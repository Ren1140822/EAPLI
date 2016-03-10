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
public class Range implements ValueObject {

    private final Comparable start;
    private final Comparable end;
    private final boolean openStart;
    private final boolean openEnd;

    public static Range openRange(Comparable start, Comparable end) {
        return new Range(start, end, true, true);
    }

    public static Range closedRange(Comparable start, Comparable end) {
        return new Range(start, end, false, false);

    }

    public static Range openEnded(Comparable start, Comparable end) {
        return new Range(start, end, false, true);
    }

    public Range(Comparable start, Comparable end, boolean openStart, boolean openEnd) {
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

    boolean includes(Comparable target) {
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
