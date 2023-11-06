package second_lab.app;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public final class ImmutableMatrix {
	private final double[][] matrix;
	private final int row;
	private final int column;

	public ImmutableMatrix() {
		this.row = 0;
		this.column = 0;
		this.matrix = new double[0][0];
	}

	public ImmutableMatrix(int row, int column) {
		this.row = row;
		this.column = column;
		this.matrix = new double[this.row][this.column];
	}

	public ImmutableMatrix(double[][] array) {
		this.row = array.length;
		this.column = array[0].length;
		this.matrix = new double[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = array[i][j];
			}
		}
	}

	public ImmutableMatrix(ImmutableMatrix matrixClone) {
		int matrixLength = matrixClone.matrix.length;

		this.column = matrixClone.column;
		this.row = matrixClone.row;
		this.matrix = new double[matrixLength][];

		for (int i = 0; i < matrixLength; i++) {
			this.matrix[i] = new double[matrixClone.matrix[i].length];
			System.arraycopy(matrixClone.matrix[i], 0, this.matrix[i], 0, matrixClone.matrix[i].length);
		}
	}

	public void setColumn(int column) throws IllegalArgumentException {
		System.out.println("Цей метод не працює в цьому классі");
	}

	public void setRow(int row) throws IllegalArgumentException {
		System.out.println("Цей метод не працює в цьому классі");
	}

	public void setMatrix() {
		if (row <= 0 || column <= 0) {
			throw new IllegalArgumentException("The number of columns and rows must be more than 0");
		} else {
			Scanner line = new Scanner(System.in);
			String elemString;
			int elemInt;

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					System.out.printf("Enter [%d][%d] element: ", i + 1, j + 1);
					elemString = line.nextLine();
					elemInt = Integer.parseInt(elemString);
					if (elemInt <= 0) {
						throw new IllegalArgumentException("The number of columns and rows must be more than 0");
					} else {
						matrix[i][j] = elemInt;
					}
				}
			}
		}
	}

	public void getMatrix() {
		for (int i = 0; i < this.matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println(" ");
	}

	public double getElem(int row, int column) {
		return matrix[row - 1][column - 1];
	}

	public double[] getRowElems(int row) {
		int matrixLength = this.matrix[row].length;
		double[] rowArray = new double[matrixLength];
		for (int c = 0; c < this.matrix[row].length; c++) {
			rowArray[c] = this.matrix[row - 1][c];
		}

		return rowArray;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public double[] getColumnElems(int column) {
		double[] columnArray = new double[this.row];

		for (int r = 0; r < this.row; r++) {
			columnArray[r] = this.matrix[r][column - 1];
		}
		return columnArray;
	}

	public void getDimention() {
		System.out.printf("%dx%d \n", this.row, this.column);
	}

	@Override
	public int hashCode() {
		final int prime = 44;
		String resultString = "1";
		// int resultInt = 1;

		resultString += String.valueOf(row);
		resultString += String.valueOf(column);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				resultString += String.valueOf(this.matrix[i][j]);
			}
		}

		int resultInt = Integer.parseInt(resultString);
		resultInt = resultInt * prime;

		return resultInt;
	}

	@Override
	public boolean equals(Object obj) {
		ImmutableMatrix compMatrix = (ImmutableMatrix) obj;

		if ((compMatrix.row != this.row) & (compMatrix.column != this.column)) {
			return false;
		} else if (obj == null) {
			return false;
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (compMatrix.matrix[i][j] != this.matrix[i][j]) {
					return false;
				}

			}
		}

		return true;
	}

	public ImmutableMatrix add(ImmutableMatrix other) {
		if (this.row != other.row || this.column != other.column) {
			throw new IllegalArgumentException("Матриці мають різний розмір");
		}

		ImmutableMatrix result = new ImmutableMatrix(this.row, this.column);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
			}
		}
		return result;
	}

	public ImmutableMatrix scalarMultiply(int scalar) {
		ImmutableMatrix result = new ImmutableMatrix(this.row, this.column);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[i][j] = this.matrix[i][j] * scalar;
			}
		}
		return result;
	}

	public ImmutableMatrix multiply(ImmutableMatrix other) {
		int n = this.row;
		int m = other.column;
		int p = other.row;

		if (m != p) {
			throw new IllegalArgumentException(
					"The number of columns in matrix A must be equal to the number of rows in matrix B");
		}

		ImmutableMatrix result = new ImmutableMatrix(n, m);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result.matrix[i][j] = 0;
				for (int k = 0; k < p; k++) {
					result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				double num = result.matrix[i][j];
				result.matrix[i][j] = Math.round(num * 100.0) / 100.0;
			}
		}
		return result;
	}

	public ImmutableMatrix transpose() {
		ImmutableMatrix result = new ImmutableMatrix(this.column, this.row);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[j][i] = this.matrix[i][j];
			}
		}
		return result;
	}

	public static ImmutableMatrix diagonalMatrix(double[] vector) {
		int n = vector.length;
		ImmutableMatrix result = new ImmutableMatrix(n, n);
		for (int i = 0; i < n; i++) {
			result.matrix[i][i] = vector[i];
		}
		return result;
	}

	public static ImmutableMatrix identityMatrix(int size) {
		ImmutableMatrix result = new ImmutableMatrix(size, size);
		for (int i = 0; i < size; i++) {
			result.matrix[i][i] = 1;
		}
		return result;
	}

	public static ImmutableMatrix randomRowMatrix(int size) {
		ImmutableMatrix result = new ImmutableMatrix(1, size);
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			result.matrix[0][i] = rand.nextInt(100);
		}
		return result;
	}

	public static ImmutableMatrix randomColumnMatrix(int size) {
		ImmutableMatrix result = new ImmutableMatrix(size, 1);
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			result.matrix[i][0] = rand.nextInt(100);
		}
		return result;
	}

	private ImmutableMatrix minorMatrix(int row, int col) {
		if (row < 0 || row >= this.row || col < 0 || col >= this.column) {
			throw new IllegalArgumentException("Недійсні індекси рядка або стовпця.");
		}

		ImmutableMatrix minor = new ImmutableMatrix(this.row - 1, this.column - 1);

		int m = 0;
		int n;

		for (int i = 0; i < this.row; i++) {
			if (i == row) {
				continue;
			}
			n = 0;
			for (int j = 0; j < this.column; j++) {
				if (j == col) {
					continue;
				}
				minor.matrix[m][n] = this.matrix[i][j];
				n++;
			}
			m++;
		}

		return minor;
	}

	private static double determinant(ImmutableMatrix matrix) {

		if (matrix.row != matrix.column) {

			throw new IllegalArgumentException("Матриця не є квадратною");
		}

		int n = matrix.row;
		if (n == 1) {

			return matrix.matrix[0][0];
		}

		if (n == 2) {

			return matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[0][1] * matrix.matrix[1][0];
		}

		double det = 0;
		for (int i = 0; i < n; i++) {

			det += matrix.matrix[0][i] * Math.pow(-1, i) * determinant(matrix.minorMatrix(0, i));
		}

		return det;
	}

	public static ImmutableMatrix inverse(ImmutableMatrix other) {
		int n = other.row;
		ImmutableMatrix idMatrix = new ImmutableMatrix(n, n);
		ImmutableMatrix auMatrix = new ImmutableMatrix(n, 2 * n);
		double determinant = ImmutableMatrix.determinant(other);

		if (determinant == 0) {
			throw new IllegalArgumentException("Матриця вироджена");

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				auMatrix.matrix[i][j] = other.matrix[i][j];
				idMatrix.matrix[i][j] = (i == j) ? 1.0 : 0.0;
				auMatrix.matrix[i][j + n] = idMatrix.matrix[i][j];
			}
		}

		for (int i = 0; i < n; i++) {

			int pivotRow = i;
			for (int j = i + 1; j < n; j++) {
				if (Math.abs(auMatrix.matrix[j][i]) > Math.abs(auMatrix.matrix[pivotRow][i])) {
					pivotRow = j;
				}
			}

			double[] tRow = auMatrix.matrix[i];
			auMatrix.matrix[i] = auMatrix.matrix[pivotRow];
			auMatrix.matrix[pivotRow] = tRow;

			double pivotElem = auMatrix.matrix[i][i];
			for (int j = 0; j < 2 * n; j++) {
				auMatrix.matrix[i][j] /= pivotElem;
			}

			for (int j = 0; j < n; j++) {
				if (j != i) {
					double factor = auMatrix.matrix[j][i];
					for (int k = 0; k < 2 * n; k++) {
						auMatrix.matrix[j][k] -= factor * auMatrix.matrix[i][k];
					}
				}
			}
		}

		ImmutableMatrix inverse = new ImmutableMatrix(n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				inverse.matrix[i][j] = auMatrix.matrix[i][j + n];
			}
		}

		return inverse;
	}
}