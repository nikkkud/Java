package second_lab.app;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Matrix {
	private int column;
	private int row;
	private double[][] matrix;

	public Matrix() {
		this.row = 0;
		this.column = 0;
		this.matrix = new double[0][0];
	}

	public Matrix(int row, int column) {
		this.row = row;
		this.column = column;
		this.matrix = new double[this.row][this.column];
	}

	public Matrix(double[][] array) {
		this.row = array.length;
		this.column = array[0].length;
		this.matrix = new double[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = array[i][j];
			}
		}
	}

	public Matrix(Matrix matrixClone) {
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
		if (column <= 0) {
			throw new IllegalArgumentException("The digit must be more than 0");
		} else {
			this.column = column;
		}
	}

	public void setRow(int row) throws IllegalArgumentException {
		if (row <= 0) {
			throw new IllegalArgumentException("The digit must be more than 0");
		} else {
			this.row = row;
		}

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

		System.out.println(" ");
	}

	public void getMatrix() {
		for (int i = 0; i < this.matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		System.out.println(" ");
	}

	public double getElem(int row, int column) {
		double matrixElem = matrix[row - 1][column - 1];
		return matrixElem;
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

	public String getDimention() {
		// System.out.printf("%dx%d", this.row, this.column);
		String row = Integer.toString(this.row);
		String column = Integer.toString(this.column);
		String Dimention = row + "x" + column;
		return Dimention;
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
				int element = (int) this.matrix[i][j];
				resultString += String.valueOf(element);
			}
		}

		int resultInt = Integer.parseInt(resultString);
		resultInt = resultInt * prime;

		return resultInt;
	}

	@Override
	public boolean equals(Object obj) {
		Matrix compMatrix = (Matrix) obj;

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

	public Matrix add(Matrix other) {
		if (this.row != other.row || this.column != other.column) {
			throw new IllegalArgumentException("Матриці мають різний розмір");
		}

		Matrix result = new Matrix(this.row, this.column);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
			}
		}
		return result;
	}

	public Matrix scalarMultiply(int scalar) {
		Matrix result = new Matrix(this.row, this.column);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[i][j] = this.matrix[i][j] * scalar;
			}
		}
		return result;
	}

	public Matrix transpose() {
		Matrix result = new Matrix(this.column, this.row);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				result.matrix[j][i] = this.matrix[i][j];
			}
		}
		return result;
	}

	public static Matrix diagonalMatrix(double[] vector) {
		int n = vector.length;
		Matrix result = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			result.matrix[i][i] = vector[i];
		}
		return result;
	}

	public static Matrix identityMatrix(int size) {
		Matrix result = new Matrix(size, size);
		for (int i = 0; i < size; i++) {
			result.matrix[i][i] = 1;
		}
		return result;
	}

	public static Matrix randomRowMatrix(int size) {
		Matrix result = new Matrix(1, size);
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			result.matrix[0][i] = rand.nextInt(100);
		}
		return result;
	}

	public static Matrix randomColumnMatrix(int size) {
		Matrix result = new Matrix(size, 1);
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			result.matrix[i][0] = rand.nextInt(100);
		}
		return result;
	}

	public Matrix multiply(Matrix other) {
		int n = this.row;
		int m = other.column;
		int p = other.row;

		if (m != p) {
			throw new IllegalArgumentException(
					"The number of columns in matrix A must be equal to the number of rows in matrix B");
		}

		Matrix result = new Matrix(n, m);

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

	public static Matrix inverse(Matrix other) {
		int n = other.row;
		Matrix identityMatrix = new Matrix(n, n);
		Matrix augmentedMatrix = new Matrix(n, 2 * n);

		// double[][] identityMatrix = new double[n][n];
		// double[][] augmentedMatrix = new double[n][2 * n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				augmentedMatrix.matrix[i][j] = other.matrix[i][j];
				identityMatrix.matrix[i][j] = (i == j) ? 1.0 : 0.0;
				augmentedMatrix.matrix[i][j + n] = identityMatrix.matrix[i][j];
			}
		}

		for (int i = 0; i < n; i++) {

			int pivotRow = i;
			for (int j = i + 1; j < n; j++) {
				if (Math.abs(augmentedMatrix.matrix[j][i]) > Math.abs(augmentedMatrix.matrix[pivotRow][i])) {
					pivotRow = j;
				}
			}

			double[] tempRow = augmentedMatrix.matrix[i];
			augmentedMatrix.matrix[i] = augmentedMatrix.matrix[pivotRow];
			augmentedMatrix.matrix[pivotRow] = tempRow;

			double pivotElement = augmentedMatrix.matrix[i][i];
			for (int j = 0; j < 2 * n; j++) {
				augmentedMatrix.matrix[i][j] /= pivotElement;
			}

			for (int j = 0; j < n; j++) {
				if (j != i) {
					double factor = augmentedMatrix.matrix[j][i];
					for (int k = 0; k < 2 * n; k++) {
						augmentedMatrix.matrix[j][k] -= factor * augmentedMatrix.matrix[i][k];
					}
				}
			}
		}

		Matrix inverse = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				inverse.matrix[i][j] = augmentedMatrix.matrix[i][j + n];
			}
		}

		return inverse;
	}

	public void setElemMatrix(int row, int column, double digit) {
		this.matrix[row - 1][column - 1] = digit;
	}

}
