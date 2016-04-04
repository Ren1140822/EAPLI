/**
 *
 */
package eapli.framework.domain;

import java.util.Calendar;

import javax.persistence.Embeddable;

/**
 *
 * @author pgsou_000
 *
 */
@Embeddable
public class TimePeriod extends Range<Calendar> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TimePeriod(Calendar begin, Calendar end) {
        super(begin, end, true, true);
    }
}
