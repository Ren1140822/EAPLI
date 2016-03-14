/**
 * 
 */
package eapli.util.math;

import java.util.Arrays;

/**
 * @author pgsou_000
 *
 */
public class Vector {
	public enum VectorType {
		Row, Column
	};

	public Vector(double[] src, VectorType type) {
		this.NElems = src.length;
		this.type = type;
		this.data = Arrays.copyOf(src, NElems);
	}

	// indexes are 1-based
	public double Element(int i) {
		return At(i - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != Vector.class)
			return false;

		Vector other = (Vector) obj;
		if (this.NElems != other.NElems || this.type != other.type)
			return false;
		for (int i = 0; i < NElems; i++)
			if (this.At(i) != other.At(i))
				return false;
		return true;
	}

	public static Vector add(Vector a, Vector b) {
		if (a.NElems != b.NElems || a.type != b.type)
			throw new IllegalStateException();

		Vector c = new Vector(a.NElems, a.type);
		for (int i = 0; i < a.NElems; i++)
			c.PutAt(i, a.At(i) + b.At(i));
		return c;
	}

	public static Vector subtract(Vector a, Vector b) {
		if (a.NElems != b.NElems || a.type != b.type)
			throw new IllegalStateException();

		Vector c = new Vector(a.NElems, a.type);
		for (int i = 0; i < a.NElems; i++)
			c.PutAt(i, a.At(i) - b.At(i));
		return c;
	}

	// return the dot product of two vectors
	public static double multiply(Vector A, Vector B) {
		if (A.NElems != B.NElems || A.type != B.type)
			throw new IllegalStateException();

		double accum = 0;
		for (int i = 0; i < A.NElems; i++)
			accum += (A.Element(i) * B.Element(i));
		return accum;
	}

	// return a new vector obtained by multiplying a vector by a scalar
	public static Vector multiply(double k, Vector A) {
		return MultByScalar(A, k);
	}

	// return a new vector obtained by multiplying a vector by a scalar
	public static Vector multiply(Vector A, double k) {
		return MultByScalar(A, k);
	}

	// return a new vector obtained by multiplying a vector by a scalar
	private static Vector MultByScalar(Vector A, double k) {
		Vector c = new Vector(A.NElems, A.type);
		for (int i = 0; i < A.NElems; i++)
			c.PutAt(i, k * A.At(i));
		return c;
	}

	public boolean isUnit() {
		double accum = 0;
		for (double x : data)
			accum += (x * x);
		return java.lang.Math.sqrt(accum) == 1.0;
	}

	/* multiply vector by scalar double. changes this object */
	public Vector Scale(double scalar) {
		for (int i = 0; i < NElems; i++)
			data[i] *= scalar;
		return this;
	}

	Vector(int n, VectorType type) {
		this.NElems = n;
		this.type = type;
		this.data = new double[NElems];
	}

	// indexes are 0-based internal
	double At(int i) {
		return data[i];
	}

	void PutAt(int i, double v) {
		data[i] = v;
	}

	private int		   NElems;
	private double[]   data;
	private VectorType type;
}
