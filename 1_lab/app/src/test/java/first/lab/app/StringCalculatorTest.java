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
	void TestOTestWithoutOptionalDelimiter() {
		StringCalculator calculator = new StringCalculator();
		int oneNumber = calculator.add("200,30\\n-200");
		Assertions.assertEquals(30, oneNumber);
	}

	@Test
	void TestWithOptionalDelimiter() {
		StringCalculator calculator = new StringCalculator();
		int numbers = calculator.add("//:\\n33\\n-33,-50:60");
		Assertions.assertEquals(10, numbers);
	}
}
