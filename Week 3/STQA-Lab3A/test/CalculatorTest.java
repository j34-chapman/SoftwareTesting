import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator c;

    //Creates an instance before each test method  - arrange mthod is already done
    @BeforeEach
    void beforeEachTestMethod(){
        c = new Calculator();
    }

    @Test
    void division() {
        //Arrange - initlaise variables , create set up tasks
        //Calculator c = new Calculator();

        //Act - peform the action you want to test
        int result = c.division(6, 3);

        //Assert - assert the actuion in the act phase produced the corrected result
        assertEquals(2, result, "6/3 didnt not produce tw0");

    }

    @Test
    @DisplayName("Division by zero")
    void testIntegerDivision_WhenDividedByZero_ShouldThrowArithmeticException() {
        // Arrange - initialize variables, create set up tasks
        //Calculator c = new Calculator();

        int dividend = 9;
        int divisor = 0;

        // Act - perform the action you want to test
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            c.division(dividend, divisor); // Use the 'c' variable here
        }, "Division by zero should throw an arithmetic exception");

        // Assert - verify that an exception was thrown with the expected message
        assertEquals("/ by zero", actualException.getMessage(), "Unexpected exception message");
    }



}
