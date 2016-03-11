/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

import eapli.framework.patterns.domain.ValueObject;

/**
 *
 * @author pgsou_000
 */
public class Range<T extends Comparable<T>> implements ValueObject {

    private final T start;
    private final T end;
    private final boolean openStart;
    private final boolean openEnd;

    public static<T extends Comparable<T>> Range<T> openRange(T start, T end) {
        return new Range<T>(start, end, true, true);
    }

    public static<T extends Comparable<T>> Range<T> closedRange(T start, T end) {
        return new Range<T>(start, end, false, false);

    }

    public static<T extends Comparable<T>> Range<T> openEnded(T start, T end) {
        return new Range<T>(start, end, false, true);
    }

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
}
