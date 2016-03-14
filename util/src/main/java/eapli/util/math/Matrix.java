/**
 * 
 */
package eapli.util.math;

/**
 * @author pgsou_000
 *
 */
public class Matrix {

	public Matrix(double[][] elems) {
		this.rows = elems.length + 1;
		this.cols = elems[0].length + 1;
		this.data = new double[rows][cols];
		CopyData(elems, data);
	}

	// indexes are 1-based
	public double elementAt(int i, int j) {
		return At(i - 1, j - 1);
	}

	public int size() {
		return rows * cols;
	}

	/* creates a square identity matrix */
	public static Matrix Identity(int n) {
		Matrix id = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				id.PutAt(i, j, 0.0);
			id.PutAt(i, i, 1.0);
		}
		return id;
	}

	/* creates a square zero matrix */
	public static Matrix Zero(int n) {
		return Zero(n, n);
	}

	public Vector Row(int index) {
		if (index < 0 || index >= rows)
			throw new IllegalArgumentException("index");
		Vector vec = new Vector(cols, Vector.VectorType.Row);
		for (int i = 0; i < cols; i++)
			vec.PutAt(i, this.At(index, i));
		return vec;
	}

	public Vector Column(int index) {
		if (index < 0 || index >= cols)
			throw new IllegalArgumentException("index");
		Vector vec = new Vector(rows, Vector.VectorType.Column);
		for (int i = 0; i < rows; i++)
			vec.PutAt(i, this.At(i, index));
		return vec;
	}

	/* creates a zero matrix */
	public static Matrix Zero(int r, int c) {
		Matrix z = new Matrix(r, c);
		return z;
	}

	/* creates a "zero" matrix with Ats with the same given value */
	public static Matrix Zero(int r, int c, double zero) {
		Matrix z = new Matrix(r, c);
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				z.PutAt(i, j, zero);
		return z;
	}

	// return a new matrix obtained by multiplying a matrix by a scalar
	private static Matrix MultByScalar(Matrix A, double k) {
		Matrix c = new Matrix(A.rows, A.cols);
		for (int i = 0; i < A.rows; i++)
			for (int j = 0; j < A.cols; j++)
				c.PutAt(i, j, k * A.At(i, j));
		return c;
	}

	/* multiply matrix by scalar double. changes this object */
	public Matrix Scale(double scalar) {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				data[i][j] *= scalar;
		return this;
	}

	/* add two matrices giving a third matrix */
	public Matrix Add(Matrix other) {
		if (this.rows != other.rows || this.cols != other.cols)
			throw new IllegalStateException("matrices must be same dimension");

		Matrix dest = new Matrix(rows, cols);

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				dest.PutAt(i, j, this.At(i, j) + other.At(i, j));
		return dest;
	}

	/* subtract two matrices giving a third matrix */
	public Matrix Subtract(Matrix other) {
		if (this.rows != other.rows || this.cols != other.cols)
			throw new IllegalStateException("matrices must be same dimension");

		Matrix dest = new Matrix(rows, cols);

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				dest.PutAt(i, j, this.At(i, j) - other.At(i, j));
		return dest;
	}

	/* multiply two matrices giving a third one */
	public Matrix Multiply(Matrix other) {
		if (this.cols != other.rows)
			throw new IllegalStateException("matrix 1 cols must = matrix 2 rows");

		Matrix dest = new Matrix(this.rows, other.cols);

		for (int i = 0; i < this.rows; i++)
			for (int j = 0; j < other.cols; j++) {
				double cellval = 0.0;
				for (int k = 0; k < this.cols; k++) {
					cellval += this.At(i, k) * other.At(k, j);
				}
				dest.PutAt(i, j, cellval);
			}
		return dest;
	}

	/* The trace of a square matrix is the sum of its diagonal elements */
	public double Trace() {
		if (!isSquare())
			throw new IllegalStateException("matrix should be square");

		double trace = 0;
		for (int i = 0; i < rows; i++)
			trace += data[i][i];
		return trace;
	}

	// indexes are 1-based
	public double Cofactor(int i, int j) {
		// (-1)^(i+j)*Mij
		return java.lang.Math.pow(-1, i + j) * Minor(i, j);
	}

	// indexes are 1-based
	public double Minor(int i, int j) {
		return this.Submatrix(i, j).Determinant1();
	}

	public Matrix Transpose() {
		Matrix dest = new Matrix(cols, rows);

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				dest.PutAt(j, i, this.At(i, j));
		return dest;
	}

	// // returns a diagonal matrix with the same elements in this matrix's
	// diagonal
	// public Matrix Diagonal()
	// {
	// if (!IsSquare)
	// throw new InvalidOperationException("matrix should be square");
	//
	// Matrix diag = Matrix.Zero(rows, cols);
	// for (int i = 0; i < rows; i++)
	// diag.PutAt(i, i, this.At(i, i));
	// return diag;
	// }

	// returns a row vector (a matrix with just one row) with the elements in
	// this matrix's diagonal
	public Matrix DiagonalElements() {
		// if (!IsSquare)
		// throw new InvalidOperationException("matrix should be square");
		int k = java.lang.Math.min(rows, cols);

		Matrix diag = Matrix.Zero(1, k + 1);
		for (int i = 0; i <= k; i++)
			diag.PutAt(0, i, this.At(i, i));
		return diag;
	}

	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix Submatrix(int index, Vector.VectorType typ) {
		switch (typ) {
		case Row:
			return WithoutRow(index);

		case Column:
			return WithoutColumn(index);

		}
		throw new IllegalStateException("should not happen - unknown VectorType");
	}

	// indexes are 1-based
	public Matrix Submatrix(int rowToRemove, int colToRemove) {
		if (rowToRemove <= 0 || rowToRemove > rows)
			throw new IllegalArgumentException("rowToRemove");
		if (colToRemove <= 0 || colToRemove > cols)
			throw new IllegalArgumentException("colToRemove");
		if (rows - 1 <= 0 || cols - 1 <= 0)
			throw new IllegalStateException("cannot remove only row/column");

		Matrix sub = new Matrix(rows - 1, cols - 1);
		int desti = 0;
		for (int i = 0; i < rows; i++) {
			if (i != rowToRemove - 1) {
				int destj = 0;
				for (int j = 0; j < cols; j++) {
					if (j != colToRemove - 1) {
						sub.PutAt(desti, destj, this.At(i, j));
						destj++;
					}
				}
				desti++;
			}
		}
		return sub;
	}

	// indexes are 1-based
	public Matrix Submatrix(int startRow, int endRow, int startCol, int endCol) {
		if (startRow <= 0 || startRow > rows)
			throw new IllegalArgumentException("startRow");
		if (endRow <= startRow || endRow > rows)
			throw new IllegalArgumentException("endRow");
		if (startCol <= 0 || startCol > cols)
			throw new IllegalArgumentException("startCol");
		if (endCol <= startCol || endCol > cols)
			throw new IllegalArgumentException("endCol");

		Matrix sub = new Matrix(endRow - startRow, endCol - startCol);
		for (int i = startRow - 1; i < endRow; i++)
			for (int j = startCol - 1; j < endCol; j++)
				sub.PutAt(i - startRow + 1, j - startCol + 1, this.At(i, j));
		return sub;
	}

	/* The vector formed by concatenating all the columns of the matrix */
	public Matrix Vectorize() {
		Matrix vec = new Matrix(rows * cols, 1);
		int k = 0;
		for (int j = 0; j < cols; j++)
			for (int i = 0; i < rows; i++) {
				vec.PutAt(k, 0, this.At(i, j));
				k++;
			}
		return vec;
	}

	public boolean isUpperDiagonal() {
		for (int i = 1; i < rows; i++)
			for (int j = 0; j < i; j++)
				if (At(i, j) != 0)
					return false;
		return true;
	}

	public boolean isLowerDiagonal() {
		for (int i = 0; i < rows - 1; i++)
			for (int j = i + 1; j < cols; j++)
				if (At(i, j) != 0)
					return false;
		return true;
	}

	public boolean isInversible() {
		// X*X' = X'*X = I

		if (!isSquare())
			return false;

		Matrix inv = this.Inverse1();
		Matrix t1 = this.Multiply(inv);
		return t1.equals(inv.Multiply(this)) && t1.isIdentity();
	}

	public Matrix Inverse2() {
		if (!isSquare())
			throw new IllegalStateException("matrix must be square");

		// Formula used to Calculate Inverse:
		// inv(A) = 1/det(A) * adj(A)
		double det = Determinant2();
		if (det == 0)
			throw new IllegalStateException("Determinant Equals 0, Not Invertible.");

		return Adjoint().Scale(1 / det);
	}

	public Matrix Adjoint() {
		if (!isSquare())
			throw new IllegalStateException("matrix must be square");

		Matrix adj = new Matrix(rows, cols);

		int ii, jj, ia, ja;
		double det;
		int tms = rows;

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				ia = ja = 0;

				Matrix ap = new Matrix(tms - 1, tms - 1);

				for (ii = 0; ii < tms; ii++) {
					for (jj = 0; jj < tms; jj++) {

						if ((ii != i) && (jj != j)) {
							ap.PutAt(ia, ja, this.At(ii, jj));
							ja++;
						}

					}
					if ((ii != i) && (jj != j)) {
						ia++;
					}
					ja = 0;
				}

				det = ap.Determinant2();
				adj.PutAt(i, j, java.lang.Math.pow(-1, i + j) * det);
			}

		return adj.Transpose();
	}

	public Matrix UpperTriangle() {
		if (!isSquare())
			throw new IllegalStateException("matrix must be square");

		double f1 = 0;
		double temp = 0;
		int tms = rows; // get This Matrix Size
		int v = 1;

		iDF = 1;

		Matrix m;
		try {
			m = (Matrix) this.clone();
		} catch (CloneNotSupportedException e1) {
			// should never happen...
			e1.printStackTrace();
			return null;
		}

		for (int col = 0; col < tms - 1; col++) {
			for (int row = col + 1; row < tms; row++) {
				v = 1;

				/* outahere: */ while (m.At(col, col) == 0) // check if 0 in
				                                            // diagonal
				{
					// if so switch until not
					if (col + v >= tms) // check if switched all rows
					{
						iDF = 0;
						// break outahere;
						continue;
					} else {
						for (int c = 0; c < tms; c++) {
							temp = m.At(col, c);
							m.PutAt(col, c, m.At(col + v, c)); // switch rows
							m.PutAt(col + v, c, temp);
						}
						v++; // count row switchs
						iDF = iDF * -1; // each switch changes determinant
						                // factor
					}
				}

				if (m.At(col, col) != 0) {
					try {
						f1 = (-1) * m.At(row, col) / m.At(col, col);
						for (int i = col; i < tms; i++) {
							m.PutAt(row, i, f1 * m.At(col, i) + m.At(row, i));
						}
					} catch (Exception e) {
						// System.out.println("Still Here!!!");
					}
				}
			}
		}

		return m;
	}

	public double Determinant2() {
		Matrix triang = UpperTriangle();

		double det = 1;
		for (int i = 0; i < rows; i++) {
			det *= triang.At(i, i);
		} // multiply down diagonal

		det *= iDF; // adjust w/ determinant factor

		return det;
	}

	private int iDF = 0; // determinant factor

	public int columnCount() {
		return cols;
	}

	public int rowCount() {
		return rows;
	}

	public boolean isSquare() {
		return rows == cols;
	}

	public boolean isSingular() {
		return Determinant1() == 0.0;
	}

	public boolean isDiagonal() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (data[i][j] != 0.0 && i != j)
					return false;
			}
		return true;
	}

	public boolean isDiagonallyDominant() {
		if (!isSquare())
			throw new IllegalStateException();

		boolean flagIneq = false;
		for (int i = 0; i < rows; i++) {
			double accum = 0.0;
			for (int j = 0; j < cols; j++) {
				if (i != j)
					accum += java.lang.Math.abs(data[i][j]);
			}
			if (java.lang.Math.abs(data[i][i]) < accum)
				return false;
			if (java.lang.Math.abs(data[i][i]) > accum)
				flagIneq = true;
		}
		return flagIneq;
	}

	public boolean isSimetric() {
		// return this.Equals(this.Transpose());

		if (!isSquare())
			throw new IllegalStateException("must be square");

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (elementAt(i, j) != elementAt(j, i))
					return false;
		return true;
	}

	public boolean isSkewSimetric() {
		// return this.Equals(this.Transpose().Scale(-1));

		if (!isSquare())
			throw new IllegalStateException("must be square");

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (elementAt(i, j) + elementAt(j, i) != 0)
					return false;
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != Matrix.class)
			return false;

		Matrix other = (Matrix) obj;
		if (this.rows != other.rows || this.cols != other.cols)
			return false;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (this.At(i, j) != other.At(i, j))
					return false;
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public boolean isIdentity() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (data[i][j] != 0.0 && i != j)
					return false;
				if (i == j && data[i][j] != 1.0)
					return false;
			}
		return true;
	}

	public boolean isZero() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (data[i][j] != 0.0)
					return false;
			}
		return true;
	}

	public double Determinant1() {
		if (!isSquare())
			throw new IllegalStateException("matrix must be square");

		double d = 0;
		for (int i = 0; i < cols; i++) {
			double p1 = 1.0;
			double p2 = 1.0;
			double p3 = At(i, 0);
			int k = i;
			for (int j = 1; j < cols; j++) {
				k = (k + 1) % cols;
				p1 *= At(j, k);
				p2 *= At(cols - j, k);
			}
			p3 *= (p1 - p2);
			d += p3;
		}
		return d;
	}

	/* ??? */
	public Matrix Inverse1() {
		if (isSingular())
			throw new IllegalStateException("matrix is singular");

		Matrix dest = new Matrix(rows, cols);
		double[][] d = new double[rows][cols + 1];

		for (int i = 0; i < rows; i++) {
			int nrow = i - 1;
			int ncol = i - 1;
			for (int j = 0; j < rows; j++) {
				nrow = (nrow + 1) % rows;
				if (j == 0)
					d[j * (cols + 1)][cols] = 1;
				else
					d[j * (cols + 1)][cols] = 0;
				for (int k = 0; k < cols; k++) {
					ncol = (ncol + 1) % cols;
					d[j * (cols + 1)][k] = this.data[nrow * cols][ncol];
				}
			}
			NSolve(rows, d);
			nrow = i - 1;
			for (int j = 0; j < rows; j++) {
				nrow = (nrow + 1) % rows;
				dest.PutAt(nrow * cols, i, d[j * (cols + 1)][cols]);
			}
		}
		return dest;
	}

	private Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.data = new double[rows][cols];
	}

	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix WithoutRow(int index) {
		if (index <= 0 || index > rows)
			throw new IllegalArgumentException("index");
		if (rows - 1 <= 0)
			throw new IllegalStateException("cannot remove only row");
		Matrix sub = new Matrix(rows - 1, cols);
		int desti = 0;
		for (int i = 0; i < rows; i++) {
			if (i != index - 1) {
				for (int j = 0; j < cols; j++) {
					sub.PutAt(desti, j, this.At(i, j));
				}
				desti++;
			}
		}
		return sub;
	}

	// removes a column from the matrix
	// indexes are 1-based
	public Matrix WithoutColumn(int index) {
		if (index <= 0 || index > cols)
			throw new IllegalArgumentException("index");
		if (cols - 1 <= 0)
			throw new IllegalStateException("cannot remove only column");
		Matrix sub = new Matrix(rows, cols - 1);
		for (int i = 0; i < rows; i++) {
			int destj = 0;
			for (int j = 0; j < cols; j++) {
				if (j != index - 1) {
					sub.PutAt(i, destj, this.At(i, j));
					destj++;
				}
			}
		}
		return sub;
	}

	/* ??? */
	private void NSolve(int rows, double[][] data) {
		int i, j;

		int cols = rows + 1;
		for (i = 0; i < rows; i++) {
			for (j = i; j < rows && data[j][j] == 0.0; j++)
				;
			if (data[j][j] == 0.0)
				throw new IllegalStateException("singular matrix");
			if (j != i) {
				for (int k = 0; k < cols; k++) {
					double dtemp = data[i][k];
					data[i][k] = data[j][k];
					data[j][k] = dtemp;
				}
			}
			for (j = cols - 1; j >= 0; j--) {
				data[i][j] /= data[i][i];
			}
			for (j = i + 1; j < rows; j++) {
				for (int k = cols - 1; k >= i; k--)
					data[j][k] -= data[j][i] * data[i][k];
			}
		}
		for (i = rows - 2; i >= 0; i--) {
			for (j = cols - 2; j > i; j--) {
				data[i][cols - 1] -= data[i][j] * data[j][cols - 1];
				data[i][j] = 0;
			}
		}
	}

	// indexes are 0-based
	void PutAt(int i, int j, double v) {
		data[i][j] = v;
	}

	// indexes are 0-based
	double At(int i, int j) {
		return data[i][j];
	}

	private void CopyData(double[][] source, double[][] dest) {
		for (int i = 0; i <= data.length; i++)
			for (int j = 0; j <= data[0].length; j++)
				dest[i][j] = source[i][j];
	}

	private int		   rows;
	private int		   cols;
	private double[][] data;

}
