package database;

import daos.BookDAO;
import daos.LoanDAO;
import daos.MemberDAO;
import models.Book;
import models.Loan;
import models.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/**
 * DatabaseInitialiser is responsible for setting up the database schema and seeding it with initial data.
 */
public class DatabaseInitialiser {

    /**
     * Database connection handler.
     */
    private final DatabaseHandler dbHandler;

    private MemberDAO memberDAO = new MemberDAO();
    private BookDAO bookDAO = new BookDAO();
    private LoanDAO loanDAO = new LoanDAO();

    /**
     * Constructor initializes the database handler.
     */
    public DatabaseInitialiser() {
        dbHandler = new DatabaseHandler();
        loanDAO = new LoanDAO();
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
    }

    /**
     * Calls all the dummy data insert methods.
     */
    public void insertAllDummyData() {
        insertDummyBooks();
        insertDummyMembers();
        insertDummyLoans();
    }

    public static void main(String[] args) {
        DatabaseInitialiser initialiser = new DatabaseInitialiser();
        initialiser.dropTables();
        initialiser.createSchema();
        initialiser.insertAllDummyData();
    }


    /**
     * Creates the Members table if it does not exist.
     */
    private void createMembersTable() {
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create Members table
            String createMembersTable = "CREATE TABLE IF NOT EXISTS Members (" +
                    "MemberID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "FirstName TEXT NOT NULL, " +
                    "LastName TEXT NOT NULL, " +
                    "Email TEXT NOT NULL UNIQUE, " +
                    "Phone TEXT NOT NULL, " +
                    "AddressLine1 TEXT, " +
                    "AddressLine2 TEXT, " +
                    "TownOrCity TEXT, " +
                    "County TEXT, " +
                    "PostCode TEXT NOT NULL, " +
                    "DateRegistered DATE DEFAULT CURRENT_DATE)";
            stmt.execute(createMembersTable);

            System.out.println("Members table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Members table: " + e.getMessage());
        }
    }

    /**
     * Creates the Books table if it does not exist.
     */
    private void createBooksTable() {
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create Books table
            String createBooksTable = "CREATE TABLE IF NOT EXISTS Books (" +
                    "BookID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Title TEXT NOT NULL, " +
                    "AuthorFirstName TEXT NOT NULL, " +
                    "AuthorSurname TEXT NOT NULL, " +
                    "ISBN TEXT, " +
                    "PublishDate DATE, " +
                    "Genre TEXT, " +
                    "PublisherName TEXT NOT NULL, " +
                    "AvailabilityStatus TEXT DEFAULT 'Available', " +
                    "Condition TEXT NOT NULL)";
            stmt.execute(createBooksTable);

            System.out.println("Books table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Books table: " + e.getMessage());
        }
    }

    /**
     * Creates the Loans table if it does not exist.
     */
    private void createLoansTable() {
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create Loans table
            String createLoansTable = "CREATE TABLE IF NOT EXISTS Loans (" +
                    "LoanID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "MemberID INTEGER, " +
                    "BookID INTEGER, " +
                    "LoanDate DATE DEFAULT CURRENT_DATE, " +
                    "DueDate DATE, " +
                    "ReturnDate DATE, " +
                    "FOREIGN KEY(MemberID) REFERENCES Members(MemberID), " +
                    "FOREIGN KEY(BookID) REFERENCES Books(BookID))";
            stmt.execute(createLoansTable);

            System.out.println("Loans table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Loans table: " + e.getMessage());
        }
    }

    /**
     * Creates the Fines table if it does not exist.
     */
    private void createFinesTable() {
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create Fines table
            String createFinesTable = "CREATE TABLE IF NOT EXISTS Fines (" +
                    "FineID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "MemberID INTEGER, " +
                    "Amount REAL, " +
                    "Reason TEXT, " +
                    "DateIssued DATE DEFAULT CURRENT_DATE, " +
                    "DatePaid DATE, " +
                    "FOREIGN KEY(MemberID) REFERENCES Members(MemberID))";
            stmt.execute(createFinesTable);

            System.out.println("Fines table created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating Fines table: " + e.getMessage());
        }
    }

    /**
     * Sets up the database schema by creating necessary tables.
     */
    public void createSchema() {
        createMembersTable();
        createBooksTable();
        createLoansTable();
        createFinesTable();
    }

    /**
     * Inserts dummy data for Members table.
     */
    public void insertDummyMembers() {
        String[] firstNames = {"John", "Alice", "Robert", "Megan", "Brian"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown"};
        String[] emails = {"john.smith@example.com", "alice.johnson@example.com", "robert.williams@example.com", "megan.jones@example.com", "brian.brown@example.com"};
        String[] phones = {"0123456789", "0123456780", "0123456709", "0123456098", "0123450987"};
        String[] postCodes = {"B1 1AA", "B2 2BB", "B3 3CC", "B4 4DD", "B5 5EE"};
        String[] addressLine1 = {"123 Elm Street", "456 Oak Avenue", "789 Maple Drive", "101 Pine Lane", "202 Birch Boulevard"};
        String[] addressLine2 = {"Apt 1A", "Suite 2B", "Floor 3", "Unit 4D", "Building 5"};
        String[] townsOrCities = {"Birmingham", "Liverpool", "Manchester", "Bristol", "Leeds"};
        String[] counties = {"West Midlands", "Merseyside", "Greater Manchester", "South West", "West Yorkshire"};
        String[] dateRegistered = {"2023-03-22", "2022-07-21", "2022-03-01", "2022-12-12", "2023-05-04"};


        for (int i = 0; i < firstNames.length; i++) {
            Member member = new Member(firstNames[i], lastNames[i], emails[i], phones[i], addressLine1[i], addressLine2[i], townsOrCities[i], counties[i], postCodes[i], LocalDate.parse(dateRegistered[i]));
            memberDAO.addMember(member);
        }
    }

    /**
     * Inserts dummy data for Books table.
     */
    public void insertDummyBooks() {
        String[] titles = {
                "To Kill a Mockingbird",
                "1984",
                "Pride and Prejudice",
                "The Great Gatsby",
                "Moby-Dick",
                "The Catcher in the Rye",
                "Brave New World",
                "Ulysses",
                "The Odyssey",
                "War and Peace",
                "The Hobbit",
                "The Da Vinci Code",
                "The Secret Life of Bees",
                "A Brief History of Time",
                "The Power of Now"
        };

        String[] authorsFirstNames = {
                "Harper",
                "George",
                "Jane",
                "F. Scott",
                "Herman",
                "J. D.",
                "Aldous",
                "James",
                "Homer",
                "Leo",
                "J.R.R.",
                "Dan",
                "Sue",
                "Stephen",
                "Eckhart"
        };

        String[] authorsSurnames = {
                "Lee",
                "Orwell",
                "Austen",
                "Fitzgerald",
                "Melville",
                "Salinger",
                "Huxley",
                "Joyce",
                "",
                "Tolstoy",
                "Tolkien",
                "Brown",
                "Monk Kidd",
                "Hawking",
                "Tolle"
        };

        String[] isbns = {
                "978-0061120084",
                "978-0451524935",
                "978-1503290563",
                "978-0743273565",
                "978-1503287266",
                "978-0316769174",
                "978-0060850524",
                "978-1494405492",
                "978-0451457767",
                "978-1400079988",
                "978-0345339683",
                "978-0307277671",
                "978-0142001745",
                "978-0553380163",
                "978-1577314806"
        };

        String[] publishDates = {
                "1960-07-11",
                "1949-06-08",
                "1813-01-28",
                "1925-04-10",
                "1851-10-18",
                "1951-07-16",
                "1932-08-18",
                "1922-02-02",
                "1923-01-01",
                "1869-01-01",
                "1937-09-21",
                "2003-04-01",
                "2001-11-08",
                "1988-04-01",
                "1997-08-19"
        };

        String[] genres = {
                "Fiction",
                "Fiction",
                "Fiction",
                "Fiction",
                "Adventure",
                "Fiction",
                "Fiction",
                "Fiction",
                "Fiction",
                "Fiction",
                "Fantasy",
                "Mystery",
                "Fiction",
                "Non-Fiction",
                "Fiction"
        };

        String[] publishers = {
                "J. B. Lippincott & Co.",
                "Secker & Warburg",
                "T. Egerton",
                "Scribner",
                "Harper & Brothers",
                "Little, Brown and Company",
                "Chatto & Windus",
                "Sylvia Beach",
                "Ancient Greece",
                "The Russian Messenger",
                "George Allen & Unwin",
                "Doubleday",
                "Viking Books",
                "Bantam Books",
                "New World Library"
        };

        String[] availabilityStatuses = {
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available",
                "Available"
        };

        String[] conditions = {
                "New",
                "Good",
                "Fair",
                "New",
                "Good",
                "Good",
                "New",
                "Fair",
                "Good",
                "New",
                "Fair",
                "Good",
                "Good",
                "New",
                "Good"
        };

        for (int i = 0; i < titles.length; i++) {
            Book book = new Book(titles[i], authorsFirstNames[i], authorsSurnames[i], isbns[i], LocalDate.parse(publishDates[i]), genres[i], publishers[i], availabilityStatuses[i], conditions[i]);
            bookDAO.addBook(book);
        }
    }

    /**
     * Inserts dummy data for Loans table.
     */
    public void insertDummyLoans() {
        List<Member> allMembers = memberDAO.getAllMembers();
        List<Book> allBooks = bookDAO.getAllBooks();

        LocalDate today = LocalDate.now();

        for (int i = 0; i < 3; i++) {
            Loan loan = new Loan(allMembers.get(i).getMemberID(), allBooks.get(i).getBookID(), today, today.plusDays(14));
            loanDAO.borrowBook(loan);
        }
    }

    public void dropTables() {
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement()) {

            // Drop the Fines table
            String dropFinesTable = "DROP TABLE IF EXISTS Fines";
            stmt.execute(dropFinesTable);
            System.out.println("Fines table dropped successfully.");

            // Drop the Loans table
            String dropLoansTable = "DROP TABLE IF EXISTS Loans";
            stmt.execute(dropLoansTable);
            System.out.println("Loans table dropped successfully.");

            // Drop the Books table
            String dropBooksTable = "DROP TABLE IF EXISTS Books";
            stmt.execute(dropBooksTable);
            System.out.println("Books table dropped successfully.");

            // Drop the Members table
            String dropMembersTable = "DROP TABLE IF EXISTS Members";
            stmt.execute(dropMembersTable);
            System.out.println("Members table dropped successfully.");

        } catch (SQLException e) {
            System.err.println("Error dropping tables: " + e.getMessage());
        }
    }




}
