package first.lab.app;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringCalculator {
	int add(String numbers) {
		String[] numbersSplit;
		int numberInt;
		int sum = 0;

		int startIndex = numbers.indexOf("//");
		int endIndex = numbers.indexOf("\\n");

		if (startIndex == 0) {
			char charDelimiter = numbers.charAt(startIndex + 2);
			String stringDelimiter = Character.toString(charDelimiter);
			String newLine = numbers.substring(endIndex + 2);

			newLine = newLine.replace("\\n", stringDelimiter);
			newLine = newLine.replace(",", stringDelimiter);
			numbersSplit = newLine.split(stringDelimiter);

			if (numbersSplit.length == 0) {
				throw new IllegalArgumentException(
						"The line contains unsupported characters or doesn't contains digits!");
			}

			else if (numbers == "") {
				return 0;
			}

			else {
				for (int i = 0; i < numbersSplit.length; i++) {

					try {
						numberInt = Integer.parseInt(numbersSplit[i]);
						sum += numberInt;
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("The line contains unsupported characters!");
					}

				}
			}

		} else {
			numbers = numbers.replace("\\n", ",");
			numbersSplit = numbers.split(",");

			if (numbersSplit.length == 0) {
				throw new IllegalArgumentException(
						"The line contains unsupported characters or doesn't contains digits!");
			}

			else if (numbers == "") {
				return 0;
			}

			else {
				for (int i = 0; i < numbersSplit.length; i++) {

					try {
						numberInt = Integer.parseInt(numbersSplit[i]);
						sum += numberInt;
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("The line contains unsupported characters!");
					}

				}
			}
		}
		return sum;
	}
}
