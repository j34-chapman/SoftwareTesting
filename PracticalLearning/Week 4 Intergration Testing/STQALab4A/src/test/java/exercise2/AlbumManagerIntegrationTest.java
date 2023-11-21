package exercise2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class AlbumManagerIntegrationTest {

    private AlbumManager albumManager;

    @BeforeEach
    void setUp() throws IOException {
        albumManager = new AlbumManager("src/test/java/resources/albums.json");
    }

    // Exercise 2.1 - Test that getting an album that doesn't exist returns null as expected
    @Test
    @DisplayName("Test retrieval of non-existent album")
    void testRetrievalOfNonExistentAlbumShouldReturnNull() {
        // todo: finish implementation

        //act
        Album NWA = albumManager.getAlbum("NWA");

        //assert
        assertNull(NWA , "Album does not exist");

       /*Q- In this we have a orange failed test ! what does this mean the
        test has worked and the output has not peformed as it should or ??

        For example recipeManager test 1.2 fails red , what diffeence does
        in terms of what it fails at ? */

    }

    // Exercise 2.2 - Test that getting an album by name is case-insensitive
    @Test
    @DisplayName("Test getAlbum method is case insensitive")
    void testGetAlbumIsCaseInsensitiveShouldReturnCorrectAlbum() {
        // todo: finish implementation

        //act
        Album retrievedAlbum = albumManager.getAlbum("ABBEY ROAD");

        //assert
        assertNotNull(retrievedAlbum,"The retrievied album should not be null");
        assertEquals("Abbey Road" , retrievedAlbum.getTitle(),"The retrived album shouldnt be case sensitive");
    }

    // Exercise 2.3 - Test that getting an album that doesn't exist returns null as expected
    @Test
    @DisplayName("Test AlbumManager's constructor correctly loads albums")
    void testConstructor_ShouldLoadAlbums() {
        // todo: finish implementation

        //Assert
        Album album1 = albumManager.getAlbum("Abbey Road");
        assertNotNull(album1, "Abbey Road should be loaded");
        assertEquals("The Beatles", album1.getArtist());
        assertEquals(1969, album1.getYear());
        assertEquals("Rock", album1.getGenre());

        /*I- I guess testing a construcutor if we look at the constructor its pulling
        methods from JSON from the construcor , so if we are getting any correct data
        from the JSON it falls underneath the cirteria of the test
         */

    }

    // Exercise 2.4 - Test that giving the loadAlbums method a file that doesn't exist gives us the correct exception
    @Test
    @DisplayName("Test Constructor with invalid file path/name")
    void testConstructorWithInvalidFilePathShouldThrowRuntimeException() {
        // todo: finish implementation

        //Arrange - Create an incorrect file path
        String invalidFilePath = "src/test/java/resources/testAlbumzzz.json";

        // Act and Assert - Use assertThrows to verify that the constructor throws RuntimeException
        RuntimeException exception = assertThrows(RuntimeException.class, () -> { new AlbumManager(invalidFilePath);
        });

       /* Q - How to throw my custom error message in albumManager (ln19) because RunTimeException message
        gives me a noSuchFileExcpetion . Is this correct becuase how can I change runtime error message.
        If i dont it gives me really specific error messages, how should i approach this
        */

        // Assert - Verify the exception message or perform additional assertions if needed
        assertEquals("Run time exception: " + invalidFilePath, exception.getMessage());
    }

    // Exercise 2.5 - Test that successive calls to the loadAlbumsFromJSON method do not keep adding the albums to the ArrayList
    @Test
    @DisplayName("testLoadAlbumsFromJSONShouldClearPreviousAlbums")
    void testLoadAlbumsFromJSONShouldClearPreviousAlbums() {
        // todo: finish implementation

        // Act - Load albums from a different JSON file
        try {
            albumManager.loadAlbumsFromJSON("src/test/java/resources/albums.json");
        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }

        // Assert - Check that the previous albums are cleared
        assertNull(albumManager.getAlbum("Abbey Road"), "Album1 should not be found");
        assertNull(albumManager.getAlbum("Thriller"), "Album2 should not be found");

       // Q- This test should pass however its failing have i created this test right ?
    }


/*
   General things to learn/understnad


   () -> { new AlbumManager(invalidFilePath) }

    () indicates that the lambda expression takes no parameters.
    -> is the lambda operator, separating the parameter list from the body of the lambda expression.
    {} encloses the body of the lambda expression.

    So, the lambda expression ( ) -> { new AlbumManager(invalidFilePath) } represents a function that
     takes no parameters and, when called, executes the code inside the curly braces. In this case,
     it's creating a new instance of AlbumManager with an invalidFilePath. This lambda expression is
     then passed as an argument to the assertThrows method.

    The assertThrows method expects a Throwable (an exception) to be thrown by the code
    inside the lambda expression. If the code inside the lambda expression does not throw the expected
    exception (in this case, a RuntimeException), the test will fail. If the code does throw the expected
    exception, the exception itself is returned, and you can further inspect or assert properties of the
    exception in your test logic.*/



}