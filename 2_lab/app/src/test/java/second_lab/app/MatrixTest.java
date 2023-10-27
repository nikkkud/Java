package second_lab.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import second_lab.Matrix;

public class MatrixTest {

	@Test
	void matrixElems() {
		double[][] array = { { 1.00, 2.00 }, { 3.00, 4.00 } };
		Matrix matrix = new Matrix(array);

		Assertions.assertEquals(3.00, matrix.getElem(2, 1));

		Assertions.assertEquals(2, matrix.getRow());
		Assertions.assertEquals(2, matrix.getColumn());
	}

	@Test
	void matrixDimention() {
		double[][] array = { { 1.00, 2.00 }, { 3.00, 4.00 }, { 1.11, 2.22 } };
		Matrix matrix = new Matrix(array);

		String Dimention = matrix.getDimention();

		Assertions.assertEquals("3x2", Dimention);
	}

	@Test
	void matrixEquals() {
		double[][] array1 = { { 1.00, 2.00 }, { 3.00, 4.00 }, { 1.11, 2.22 } };
		double[][] array2 = { { 1.00, 2.00 }, { 1.11, 2.22 } };
		Matrix matrix1 = new Matrix(array1);
		Matrix matrix1Clone = new Matrix(matrix1);
		Matrix matrix2 = new Matrix(array2);

		boolean firstComp = matrix1.equals(matrix2);
		boolean secondComp = matrix1Clone.equals(matrix1);
		boolean thirdComp = matrix1Clone.equals(matrix2);

		Assertions.assertEquals(false, firstComp);
		Assertions.assertEquals(true, secondComp);
		Assertions.assertEquals(false, thirdComp);
	}

	@Test
	void matrixHashCode() {
		double[][] array = { { 1.00, 2.00 }, { 1.11, 2.22 } };
		Matrix matrix = new Matrix(array);
		int hashCode = matrix.hashCode();
		int waitHashCode = 1221212 * 44;

		Assertions.assertEquals(waitHashCode, hashCode);
	}

	@Test
	void matrixScalar() {
		double[][] array = { { 1.00, 2.00 }, { 1.11, 2.22 } };
		Matrix matrix = new Matrix(array);
		Matrix matrixScalar = matrix.scalarMultiply(3);

		int row = matrixScalar.getRow();
		int column = matrixScalar.getColumn();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				double elem = matrix.getElem(i + 1, j + 1) * 3;
				double secondElem = matrixScalar.getElem(i + 1, j + 1);
				Assertions.assertEquals(elem, secondElem);
			}
		}

	}

	@Test
	void matrixTranspose() {
		double[][] array1 = { { 1.00, 2.00 }, { 3.11, 4.22 }, { 5.11, 6.22 } };
		double[][] array2 = { { 1.00, 3.11, 5.11 }, { 2.00, 4.22, 6.22 } };

		Matrix matrix1 = new Matrix(array1);
		Matrix matrix2 = matrix1.transpose();

		int row = matrix2.getRow();
		int column = matrix2.getColumn();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				double elem = matrix2.getElem(i + 1, j + 1);

				Assertions.assertEquals(array2[i][j], elem);
			}
		}

	}

	@Test
	void matrixDiagonal() {
		double[][] array = { { 1.00, 0, 0 }, { 0, 2.00, 0 }, { 0, 0, 3.00 } };
		double[] vector = { 1.00, 2.00, 3.00 };

		Matrix matrix = Matrix.diagonalMatrix(vector);

		int row = matrix.getRow();
		int column = matrix.getColumn();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				double elem = matrix.getElem(i + 1, j + 1);

				Assertions.assertEquals(array[i][j], elem);
			}
		}

	}

	@Test
	void matrixIndentety() {
		double[][] array = { { 1.00, 0, 0 }, { 0, 1.00, 0 }, { 0, 0, 1.00 } };

		Matrix matrix = Matrix.identityMatrix(3);

		int row = matrix.getRow();
		int column = matrix.getColumn();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				double elem = matrix.getElem(i + 1, j + 1);

				Assertions.assertEquals(array[i][j], elem);
			}
		}
	}

}
