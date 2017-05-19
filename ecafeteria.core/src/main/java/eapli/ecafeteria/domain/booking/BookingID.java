package eapli.ecafeteria.domain.booking;

import eapli.framework.domain.ddd.ValueObject;
import java.util.UUID;
import javax.persistence.Embeddable;

/**
 * @FIXME javadoc
 * @FIXME create unit tests Created by henri on 5/18/2017.
 */
@Embeddable
public class BookingID implements ValueObject {

    private String id;

    private BookingID() {

    }

    //FIXME validations
    public BookingID(String id) {
        this.id = id;
    }

    public static BookingID randomBookingID() {
        return new BookingID(UUID.randomUUID().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BookingID that = (BookingID) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String toString() {
        return id;
    }
}
