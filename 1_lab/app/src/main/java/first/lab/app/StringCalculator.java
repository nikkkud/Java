package first.lab.app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
	int add(String numbers) {
		String[] numbersSplit;
		int numberInt;
		int sum = 0;

		int startIndex = numbers.indexOf("//");
		int endIndex = numbers.indexOf("\\n");
		String numberString;
		List<String> negativeNumbers = new ArrayList<>();

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
						if (numberInt >= 0) {
							if (numberInt < 1000) {
								sum += numberInt;
							}
						} else {
							numberString = Integer.toString(numberInt);
							negativeNumbers.add(numberString);
						}
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
						if (numberInt >= 0) {
							if (numberInt < 1000) {
								sum += numberInt;
							}

						} else {
							numberString = Integer.toString(numberInt);
							negativeNumbers.add(numberString);
						}
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("The line contains unsupported characters!");
					}

				}
			}
		}
		if (negativeNumbers.size() > 1) {
			String listDelimiter = ",";
			String negativeNumbersString = String.join(listDelimiter, negativeNumbers);
			throw new IllegalArgumentException(String.format("Forbidden negative numbers: %s", negativeNumbersString));

		} else {
			return sum;
		}

	}
}
