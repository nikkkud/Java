package first.lab.app;

import java.util.ArrayList;
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
			int firstBrackets = numbers.indexOf("[");
			int secondBrackets = numbers.indexOf("]");
			String delimiter = numbers.substring(firstBrackets + 1, secondBrackets);

			String newLine = numbers.substring(endIndex + 2);

			newLine = newLine.replace("\\n", ",");
			newLine = newLine.replace(delimiter, ",");
			numbersSplit = newLine.split(",");

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
		if (negativeNumbers.size() >= 1) {
			String listDelimiter = ",";
			String negativeNumbersString = String.join(listDelimiter, negativeNumbers);
			throw new IllegalArgumentException(String.format("Forbidden negative numbers: %s", negativeNumbersString));

		} else {
			return sum;
		}

	}
}
