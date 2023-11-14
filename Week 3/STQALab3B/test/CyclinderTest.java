import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CyclinderTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/calculateVolume.csv") // it works

    void testCalcualteVolume(double radius , double height , double expectedVolume){

        //arrange
        Cyclinder c = new Cyclinder();

        //act
        double actualVolume = c.calcualteVolume(radius , height);

        assertEquals(expectedVolume,actualVolume,
                "Cylinder with radius of " + radius + " and height of "
        + height + " did not produce result of " + expectedVolume +  " Instead Produced " + actualVolume );
        // Expected volume is the parmaeter input for example , 12.57 of the CSV data
    }

      /* The @ParameterizedTest annotation (note the US spelling that uses ‘z’),
      denotes a test method that replaces a standard test with multiple instances
      that run with different arguments, effectively executing the same test logic with multiple sets of data.*/

      /* The @CsvSource annotation allows us to provide comma-separated values to be used as arguments in a
      parameterised test, allowing you to specify inline data sets directly in the test code.*/

}