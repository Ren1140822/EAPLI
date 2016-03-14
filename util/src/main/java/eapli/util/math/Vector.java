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
		this.numElems = src.length;
		this.type = type;
		this.data = Arrays.copyOf(src, numElems);
	}

	// indexes are 1-based
	public double elementAt(int i) {
		return getAt(i - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != Vector.class)
			return false;

		Vector other = (Vector) obj;
		if (this.numElems != other.numElems || this.type != other.type)
			return false;
		for (int i = 0; i < numElems; i++)
			if (this.getAt(i) != other.getAt(i))
				return false;
		return true;
	}

	public Vector add(Vector b) {
		if (this.numElems != b.numElems || this.type != b.type)
			throw new IllegalStateException();

		Vector c = new Vector(this.numElems, this.type);
		for (int i = 0; i < this.numElems; i++)
			c.putAt(i, this.getAt(i) + b.getAt(i));
		return c;
	}

	public Vector subtract(Vector b) {
		if (this.numElems != b.numElems || this.type != b.type)
			throw new IllegalStateException();

		Vector c = new Vector(this.numElems, this.type);
		for (int i = 0; i < this.numElems; i++)
			c.putAt(i, this.getAt(i) - b.getAt(i));
		return c;
	}

	// return the dot product of two vectors
	public double multiply(Vector B) {
		if (this.numElems != B.numElems || this.type != B.type)
			throw new IllegalStateException();

		double accum = 0;
		for (int i = 0; i < this.numElems; i++)
			accum += (this.getAt(i) * B.getAt(i));
		return accum;
	}

	// return a new vector obtained by multiplying a vector by a scalar
	public Vector scale(double k) {
		Vector c = new Vector(this.numElems, this.type);
		for (int i = 0; i < this.numElems; i++)
			c.putAt(i, k * this.getAt(i));
		return c;
	}

	public boolean isUnit() {
		double accum = 0;
		for (double x : data)
			accum += (x * x);
		return java.lang.Math.sqrt(accum) == 1.0;
	}

	Vector(int numElems, VectorType type) {
		this.numElems = numElems;
		this.type = type;
		this.data = new double[numElems];
	}

	// indexes are 0-based internal
	double getAt(int i) {
		return data[i];
	}

	void putAt(int i, double v) {
		data[i] = v;
	}

	private int		   numElems;
	private double[]   data;
	private VectorType type;
}
