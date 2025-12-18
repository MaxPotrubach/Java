import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testAdd() throws InvalidInputException {
        assertEquals(10.0, calculator.sum(3,7));
    }
    @Test
    void testSubtract() throws InvalidInputException {
        assertEquals(4.0, calculator.sub(6, 2));
    }

    @Test
    void testMultiply() throws InvalidInputException {
        assertEquals(30.0, calculator.mul(10, 3));
    }

    @Test
    void testDivide() throws InvalidInputException {
        assertEquals(2.0, calculator.div(8, 4));
    }

    @Test
    void testSqrt() throws InvalidInputException {
        assertEquals(5.0, calculator.sqrt(25));
    }

    @Test
    void testPower() throws InvalidInputException {
        assertEquals(8, calculator.pow(2,3));
    }

    @Test
    void testAbs() throws InvalidInputException {
        assertEquals(2, calculator.abs(-2));
    }
    @Test
    void testDivideByZeroThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> calculator.div(4, 0));
    }

    @Test
    void testInvalidInputExceptionForNaN() {
        assertThrows(InvalidInputException.class, () -> calculator.sum(Double.NaN, 3));
        assertThrows(InvalidInputException.class, () -> calculator.sub(4, Double.NaN));
        assertThrows(InvalidInputException.class, () -> calculator.mul(Double.NaN, Double.NaN));
        assertThrows(InvalidInputException.class, () -> calculator.div(Double.NaN, 7));
        assertThrows(InvalidInputException.class, () -> calculator.sqrt(Double.NaN));
        assertThrows(InvalidInputException.class, () -> calculator.pow(Double.NaN, 2));
        assertThrows(InvalidInputException.class, () -> calculator.abs(Double.NaN));
    }

    @Test
    void testSqrtNegativeNumberThrowsInvalidInputException() {
        assertThrows(ArithmeticException.class, () -> calculator.sqrt(-5));
    }
}