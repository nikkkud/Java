package first.lab.app;

import java.util.Scanner;

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
	void TestException() {
		StringCalculator calculator = new StringCalculator();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			int numbers = calculator.add("//:\\n33\\n-33,-50:60");
		});
	}

	@Test
	void TestDigitsIgnore() {
		StringCalculator calculator = new StringCalculator();
		int oneNumber = calculator.add("//:\\n33\\n1033,7:1000\\n1001");
		Assertions.assertEquals(40, oneNumber);
	}

	@Test
	void TestLongDelimiter() {
		StringCalculator calculator = new StringCalculator();
		int oneNumber = calculator.add("//[;;;]\\n33\\n1033,7;;;1000\\n1001;;;10;;;");
		Assertions.assertEquals(50, oneNumber);
	}
}
