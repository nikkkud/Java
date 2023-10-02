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
		List<Integer> indexFirstBrackets = new ArrayList<>();
		List<Integer> indexSecondBrackets = new ArrayList<>();

		if (startIndex == 0) {

			for (int i = 0; i < endIndex; i++) {
				char number = numbers.charAt(i);
				char firstCharBracket = '[';
				char secondCharBracket = ']';

				if (number == firstCharBracket) {
					indexFirstBrackets.add(i);
				}

				else if (number == secondCharBracket) {
					indexSecondBrackets.add(i);
				}
			}

			boolean flag = false;
			int var1;
			int var2;

			while (!flag) {
				flag = true;
				for (int i = 0; i < indexFirstBrackets.size() - 1; i++) {
					int firstOdd = indexSecondBrackets.get(i) - indexFirstBrackets.get(i);
					int secondOdd = indexSecondBrackets.get(i + 1) - indexFirstBrackets.get(i + 1);
					if (firstOdd < secondOdd) {

						var1 = indexFirstBrackets.get(i);
						var2 = indexSecondBrackets.get(i);

						indexFirstBrackets.set(i, indexFirstBrackets.get(i + 1));
						indexFirstBrackets.set(i + 1, var1);

						indexSecondBrackets.set(i, indexSecondBrackets.get(i + 1));
						indexSecondBrackets.set(i + 1, var2);

						flag = false;
					}
				}
			}

			String newLine = numbers.substring(endIndex + 2);

			for (int i = 0; i < indexFirstBrackets.size(); i++) {
				int indexFirstBracket = indexFirstBrackets.get(i);
				int indexSecondBracket = indexSecondBrackets.get(i);
				String delimiter;

				delimiter = numbers.substring(indexFirstBracket + 1, indexSecondBracket);

				newLine = newLine.replace(delimiter, ",");

			}
			newLine = newLine.replace("\\n", ",");
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
