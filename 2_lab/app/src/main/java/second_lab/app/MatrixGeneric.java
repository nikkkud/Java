package second_lab.app;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MatrixGeneric<T> {
	private int column;
	private int row;
	private T[][] matrix;

	public MatrixGeneric(int row, int column) {
		this.row = row;
		this.column = column;
		this.matrix = (T[][]) new Object[row][column];
	}

	public MatrixGeneric(T[][] mtx) {
		this.row = mtx.length;
		this.column = mtx[0].length;
		this.matrix = (T[][]) new Object[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				matrix[i][j] = mtx[i][j];
			}
		}
	}

	public T getElem(int row, int column) {
		return matrix[row - 1][column - 1];
	}

	public T[] getRowElems(int row) {
		int rowIn = row - 1;

		if (rowIn < 0 || rowIn >= matrix.length) {
			throw new IllegalArgumentException("Invalid row index");
		}

		List<T> rowList = new ArrayList<>();
		for (int i = 0; i < matrix[rowIn].length; i++) {
			rowList.add(matrix[rowIn][i]);
		}

		T[] rowArray = (T[]) java.lang.reflect.Array.newInstance(matrix[rowIn][0].getClass(), rowList.size());

		for (int i = 0; i < rowList.size(); i++) {
			rowArray[i] = rowList.get(i);
		}

		return rowArray;
	}

	public T[] getColumnElems(int column) {
		if (column < 0 || column >= matrix[0].length) {
			throw new IllegalArgumentException("Invalid column index!");
		}

		T[] result = (T[]) Array.newInstance(matrix[0][column].getClass(), matrix.length);

		for (int i = 0; i < matrix.length; i++) {
			result[i] = matrix[i][column];
		}

		return result;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public String getDimention() {
		// System.out.printf("%dx%d \n", this.row, this.column);
		String row = Integer.toString(this.row);
		String column = Integer.toString(this.column);
		String Dimention = row + "x" + column;
		return Dimention;
	}

}
