import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BMICalculatorTest {

    private BMICalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new BMICalculator();
    }

    @Test
    @DisplayName("BMI with zero weight")
    void testCalculateBMI_WhenWeightIsZero_ShouldThrowIllegalArgumentException() {

        // Set up the input values
        double weight = 0;
        double height = 1.75;

        // Act - perform the action you want to test
        IllegalArgumentException actualException = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateBMI(weight, height),
                "Calculating BMI with zero weight should throw an IllegalArgumentException"
        );

        // Assert - verify that an exception was thrown with the expected message
        assertEquals("Weight and height must be greater than zero",
                actualException.getMessage(), "Unexpected exception message");
    }

    @Test
    @DisplayName("BMI with zero height")
    void testCalculateBMI_WhenHeightIsZero_ShouldThrowIllegalArgumentException() {

        // Set up the input values
        double weight = 100;
        double height = 0;

        // Act - perform the action you want to test
        IllegalArgumentException actualException = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateBMI(weight, height),
                "Calculating BMI with zero height should throw an IllegalArgumentException"
        );

        // Assert - verify that an exception was thrown with the expected message
        assertEquals("Weight and height must be greater than zero",
                actualException.getMessage(), "Unexpected exception message");

      /*  assertEquals(
                "Expected Message",        // Expected Exception Message
                actualException.getMessage(),  // Actual Exception Message from the thrown exception
                "Custom Error Message"      // Optional Custom Error Message
        )

        The assertEquals statement compares the "Expected Message" with the actual exception
        message obtained from actualException.

        If they are the same, the test passes. If they are different, the test fails, and the optional "Custom Error Message"
         is displayed to help you understand what's wrong.
*/

    }

    @Test
    @DisplayName("BMI with negative height")
    void testCalculateBMI_WhenHeightIsNegative_ShouldThrowIllegalArgumentException() {

        // Set up the input values
        double weight = 100;
        double height = -5;

        // Act - perform the action you want to test
        IllegalArgumentException actualException = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateBMI(weight, height),
                "Calculating BMI with negative height should throw an IllegalArgumentException"
        );

        // Assert - verify that an exception was thrown with the expected message
        assertEquals("Weight and height must be greater than zero",
                actualException.getMessage(), "Unexpected exception message");


    }

    @Test
    @DisplayName("BMI with negative height")
    void testCalculateBMI_WhenWeightIsNegative_ShouldThrowIllegalArgumentException() {

        // Set up the input values
        double weight = -100;
        double height = 5;

        // Act - perform the action you want to test
        IllegalArgumentException actualException = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.calculateBMI(weight, height),
                "Calculating BMI with negative weight should throw an IllegalArgumentException"
        );

        // Assert - verify that an exception was thrown with the expected message
        assertEquals("Weight and height must be greater than zero",
                actualException.getMessage(), "Unexpected exception message");


    }

    @Test
    @DisplayName("BMI with negative height")
    void testCalculateBMI_WhenBMILessThan18Point5_ShouldReturnUnderWeight() {

        // Arrange - no specific argument needed for this test

        // Act - perform the action you want to test
        String classification = calculator.classifyBMI(18.0);

        // Assert - verify that the classification is "Underweight"
        assertEquals("Underweight", classification, // the classification object should return "underweight" calling the method
                "BMI should be classified as Underweight");


    }

    @Test
    @DisplayName("BMI >= 18.5 && BMI < 24.9")
    void testCalculateBMI_WhenBMIBetweenThan18Point5andTwentyFourPoint9_ShouldReturnUnderWeight() {

        // Arrange - no specific argument needed for this test

        // Act - perform the action you want to test
        String classification1 = calculator.classifyBMI(18.5);

        // Act2 - perform the action you want to test
        String classification2 = calculator.classifyBMI(24.8);


        // Assert - verify that the classification is "Underweight"
        assertEquals("Normal weight", classification1, // the classification object should return "underweight" calling the method
                "BMI should be classified as Normal Weight");

        // Assert - verify that the classification is "Underweight"
        assertEquals("Normal weight", classification2, // the classification object should return "underweight" calling the method
                "BMI should be classified as Normal Weight");


    }

    // Could carry on doing the BMI ones but theyre kind of self explanatory if you look at the requirmens
}

