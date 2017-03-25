/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.range;

import eapli.framework.domain.ValueObject;
import javax.persistence.Embeddable;

/**
 * a generic range class.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 */
@Embeddable
public class Range<T extends Comparable<T>> implements ValueObject {

    protected enum BoundaryLimitType {
        INFINITY, OPEN, CLOSED
    }

    private static final long serialVersionUID = 1L;
    private T start;
    private T end;
    private BoundaryLimitType startBoundary;
    private BoundaryLimitType endBoundary;

    public static class RangeBuilder<R extends Comparable<R>> {

        private final R start;
        private R end;
        private final BoundaryLimitType startBoundary;
        private BoundaryLimitType endBoundary;

        /**
         * starts building a range at start
         *
         * @param start
         * @param startBoundary
         */
        private RangeBuilder(R start, BoundaryLimitType startBoundary) {
            assert (startBoundary == BoundaryLimitType.INFINITY && start == null)
                    || ((startBoundary != BoundaryLimitType.INFINITY && start != null));
            this.start = start;
            this.startBoundary = startBoundary;
        }

        public RangeBuilder<R> toIncluding(R anchor) {
            end = anchor;
            endBoundary = BoundaryLimitType.CLOSED;
            return this;
        }

        public RangeBuilder<R> toExcluding(R anchor) {
            this.end = anchor;
            this.endBoundary = BoundaryLimitType.OPEN;
            return this;
        }

        public RangeBuilder<R> toInfinity() {
            this.end = null;
            this.endBoundary = BoundaryLimitType.INFINITY;
            return this;
        }

        public Range<R> build() {
            return new Range<>(this.start, startBoundary, this.end, this.endBoundary);
        }
    }

    protected Range() {
        // for ORM
    }

    /**
     * constructs a range.
     *
     * @param start anchor start of the range or null to represent infinity
     * @param end anchor end of the range or null to represent infinity
     * @param startBoundary indicates if the range is open or closed at the
     * start anchor
     * @param endBoundary indicates if the range is open or closed at the end
     * anchor
     */
    protected Range(T start, BoundaryLimitType startBoundary, T end, BoundaryLimitType endBoundary) {
        if ((start == null && startBoundary != BoundaryLimitType.INFINITY)
                || (end == null && endBoundary != BoundaryLimitType.INFINITY)) {
            throw new IllegalStateException("start or end must be non-null");
        }

        if (end != null && start != null && end.compareTo(start) < 0) {
            throw new IllegalStateException("The end value of a range must be bigger than its start");
        }
        if (end != null && start != null && end.compareTo(start) == 0
                && (startBoundary == BoundaryLimitType.OPEN || endBoundary == BoundaryLimitType.OPEN)) {
            throw new IllegalStateException("An empty range is not allowed");
        }

        this.start = start;
        this.end = end;
        this.startBoundary = startBoundary;
        this.endBoundary = endBoundary;
    }

    /**
     * A factory method for ranges that start at "negative infinity"
     *
     * @return
     */
    public static <T extends Comparable<T>> RangeBuilder<T> fromInfinity() {
        return new RangeBuilder<>(null, BoundaryLimitType.INFINITY);
    }

    public static <T extends Comparable<T>> RangeBuilder<T> fromIncluding(T start) {
        return new RangeBuilder<>(start, BoundaryLimitType.CLOSED);
    }

    public static <T extends Comparable<T>> RangeBuilder<T> fromExcluding(T start) {
        return new RangeBuilder<>(start, BoundaryLimitType.OPEN);
    }

    /**
     * checks if a value belongs in this range
     *
     * @param target
     * @return
     */
    public boolean includes(T target) {
        if (startBoundary != BoundaryLimitType.INFINITY && endBoundary != BoundaryLimitType.INFINITY) {
            return regularIncludes(target);
        } else if (endBoundary == BoundaryLimitType.INFINITY) {
            return toInfinityRangeIncludes(target);
        } else {
            assert startBoundary == BoundaryLimitType.INFINITY;
            return fromInfinityRangeIncludes(target);
        }
    }

    private boolean fromInfinityRangeIncludes(T target) {
        if (target.compareTo(this.end) > 0) {
            return false;
        }
        return !(endBoundary == BoundaryLimitType.OPEN && target.compareTo(this.end) == 0);
    }

    private boolean toInfinityRangeIncludes(T target) {
        if (target.compareTo(this.start) < 0) {
            return false;
        }
        return !(startBoundary == BoundaryLimitType.OPEN && target.compareTo(this.start) == 0);
    }

    private boolean regularIncludes(T target) {
        if (target.compareTo(this.start) < 0 || target.compareTo(this.end) > 0) {
            return false;
        }
        if (startBoundary == BoundaryLimitType.OPEN && target.compareTo(this.start) == 0) {
            return false;
        }
        return !(endBoundary == BoundaryLimitType.OPEN && target.compareTo(this.end) == 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(startBracket(startBoundary == BoundaryLimitType.OPEN));
        sb.append(rangeValue(this.isFromInfinity(), this.start));
        sb.append(", ");
        sb.append(rangeValue(this.isToInfinity(), this.end));
        sb.append(endBracket(endBoundary == BoundaryLimitType.OPEN));
        return sb.toString();
    }

    public boolean isToInfinity() {
        return endBoundary == BoundaryLimitType.INFINITY;
    }

    public boolean isFromInfinity() {
        return startBoundary == BoundaryLimitType.INFINITY;
    }

    public T start() {
        return this.start;
    }

    public T end() {
        return this.end;
    }

    private char startBracket(boolean isOpen) {
        if (isOpen) {
            return ']';
        } else {
            return '[';
        }
    }

    private char endBracket(boolean isOpen) {
        if (isOpen) {
            return '[';
        } else {
            return ']';
        }
    }

    private String rangeValue(boolean isInfinity, T value) {
        if (isInfinity) {
            return "oo";
        } else {
            return value.toString();
        }
    }
}
