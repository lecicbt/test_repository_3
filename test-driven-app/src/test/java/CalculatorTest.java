

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CalculatorTest {

	 @Test
	    public void testAddition() {
	        assertEquals(5, Calculator.add(2, 3));
	        assertEquals(-1, Calculator.add(-2, 1));
	    }

	    @Test
	    public void testSubtraction() {
	        assertEquals(1, Calculator.subtract(3, 2));
	        assertEquals(-3, Calculator.subtract(-2, 1));
	    }

	    @Test
	    public void testMultiplication() {
	        assertEquals(6, Calculator.multiply(2, 3));
	        assertEquals(-2, Calculator.multiply(-1, 2));
	    }

	    @Test(expected = ArithmeticException.class)
	    public void testDivision() {
	    	assertEquals(2, Calculator.divide(6, 3));
	        Calculator.divide(1, 0); // This should throw ArithmeticException
	    }
	}

