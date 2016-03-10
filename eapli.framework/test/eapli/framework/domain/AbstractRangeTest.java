/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

/**
 *
 * @author pgsou_000
 */
public class AbstractRangeTest {

    public AbstractRangeTest() {
    }

    protected static final int START_VALUE = 5;
    protected static final int DELTA_VALUE = 5;
    protected static final int END_VALUE = START_VALUE + DELTA_VALUE;
    protected static final Comparable START = new Long(START_VALUE);
    protected static final Comparable END = new Long(END_VALUE);
    protected static Range instance;

}
