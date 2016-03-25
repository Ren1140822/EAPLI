/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain;

/**
 * a generic range class.
 *
 * @author pgsou_000
 */
public class Range<T extends Comparable<T>> implements ValueObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final T			  start;
	private final T			  end;
	private final boolean	  openStart;
	private final boolean	  openEnd;

	/**
	 * a factory method for open ranges
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static <T extends Comparable<T>> Range<T> openRange(T start, T end) {
		return new Range<T>(start, end, true, true);
	}

	/**
	 * a factory method for closed ranges
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static <T extends Comparable<T>> Range<T> closedRange(T start, T end) {
		return new Range<T>(start, end, false, false);

	}

	/**
	 * a factory method for open ended ranges
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static <T extends Comparable<T>> Range<T> openEnded(T start, T end) {
		return new Range<T>(start, end, false, true);
	}

	/**
	 * constructs a range
	 *
	 * @param start
	 *            start of the range
	 * @param end
	 *            end of the range
	 * @param openStart
	 *            indicates if the range is open at the start
	 * @param openEnd
	 *            indicates if the range is open at the end
	 */
	public Range(T start, T end, boolean openStart, boolean openEnd) {
		if (end.compareTo(start) < 0) {
			throw new IllegalStateException("The end value of a range must be bigger than its start");
		}
		if (end.compareTo(start) == 0 && (openEnd || openStart)) {
			throw new IllegalStateException("An empty range is not allowed");
		}

		this.start = start;
		this.end = end;
		this.openStart = openStart;
		this.openEnd = openEnd;
	}

	/**
	 * checks if a value belongs in this range
	 * 
	 * @param target
	 * @return
	 */
	boolean includes(T target) {
		if (target.compareTo(start) < 0 || target.compareTo(end) > 0) {
			return false;
		}
		if (openStart && target.compareTo(start) == 0) {
			return false;
		}
		if (openEnd && target.compareTo(end) == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		if (openStart) {
			sb.append(']');
		} else {
			sb.append('[');
		}
		sb.append(start);
		sb.append(", ");
		sb.append(end);

		if (openEnd) {
			sb.append('[');
		} else {
			sb.append(']');
		}
		return null;
	}
}
