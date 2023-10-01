package first.lab.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

	@Test
	void TestZero() {
		StringCalculator calculator = new StringCalculator();
		int zero = calculator.add("");
		Assertions.assertEquals(0, zero);
	}

	@Test
	void TestOneNumber() {
		StringCalculator calculator = new StringCalculator();
		int oneNumber = calculator.add("234");
		Assertions.assertEquals(234, oneNumber);
	}

	@Test
	void TestTwoNumbers() {
		StringCalculator calculator = new StringCalculator();
		int twoNumbers = calculator.add("11,33,-11,-30");
		Assertions.assertEquals(3, twoNumbers);
	}
}
