import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
class PasswordCheckerTest {

    @ParameterizedTest
    @CsvSource({
            // Weak passwords
            "abc, Weak",            // Test case 1: Password = abc
            "12345678, Weak",       // Test case 2: Password = 12345678
            "ABCDEF, Weak",         // Test case 3: Password = ABCDEF
            "!@#weak123, Weak",     // Test case 4: Password = !@#weak123   // So surely we can test for good fails i am right ?

            // Average passwords
            "Abcdefgh, Average",    // Test case 5: Password = Abcdefgh   // Here this should pe passing apperantley
            "12345678a, Average",   // Test case 6: Password = 12345678a
            "Hello123, Average",   // Test case 7: Password = AVERAGE12   // And here - so are we saying contextually there is a problem with the code or the test

            // Strong passwords
            "Strong@123, Strong",   // Test case 8: Password = Strong123!
            "AbCdEfGh!1, Strong",   // Test case 9: Password = AbCdEfGh!1
            "!@#Strong, Strong"     // Test case 10: Password = !@#Strong

    })  // Ive left this as non source file csv to demonstrte how to do it - return to lecture slides its not as easy as just creating a folder

    void testAssessPasswordStrength(String password , String expectedPasswordStrength ){

        //arrange
        PasswordChecker p = new PasswordChecker();

        //act
        String actualPasswordStrength = p.assessPasswordStrength(password);

        assertEquals(expectedPasswordStrength,actualPasswordStrength,
                "Password  of " + password +  " did not produce result of " + expectedPasswordStrength +  " Instead Produced " + actualPasswordStrength );
        // Expected volume is the parmaeter input for example , 12.57 of the CSV data
    }

    /* So in a sense im testing to see whether CSV source data (String expectedPasswordStrength)
    is correct to the method in the password checker class ?  why not the other way round when writing this ?*/

      /* The @ParameterizedTest annotation (note the US spelling that uses ‘z’),
      denotes a test method that replaces a standard test with multiple instances
      that run with different arguments, effectively executing the same test logic with multiple sets of data.*/

      /* The @CsvSource annotation allows us to provide comma-separated values to be used as arguments in a
      parameterised test, allowing you to specify inline data sets directly in the test code.*/

}