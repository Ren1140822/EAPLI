package eapli.ecafeteria.domain.cafeteria.cashregister;

import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt] Diogo Santos [1150451@isep.ipp.pt]
 */
@Entity
public class Complaint implements AggregateRoot<ComplaintId> {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    private ComplaintId id;

    private String complaint;

    private Dish dish;

    private MecanographicNumber mecanographicNumber;

    /**
     * Complaint constructor without the dish and the mecanographic number of
     * the user who complains.
     *
     * @param id the id of the complaint
     * @param complaint the text of the complaint
     */
    public Complaint(String complaint) {
        this.id = new ComplaintId();
        if (Strings.isNullOrEmpty(complaint) || Strings.isNullOrWhiteSpace(complaint)) {
            throw new IllegalStateException("The complaint must contain some sort of text!");
        }
        this.complaint = complaint;
    }

    /**
     * Complaint constructor with the dish.
     *
     * @param id the id of the complaint
     * @param complaint the text of the complaint
     * @param dish the dish of the complaint
     */
    public Complaint(String complaint, Dish dish) {
        this.id = new ComplaintId();
        if (Strings.isNullOrEmpty(complaint) || Strings.isNullOrWhiteSpace(complaint)) {
            throw new IllegalStateException("The complaint must contain some sort of text!");
        }
        this.complaint = complaint;
        if (dish == null) {
            throw new IllegalStateException("The dish can't be null!");
        }
        this.dish = dish;
    }

    /**
     * Complaint constructor with the dish with the mecanographic number of the
     * user who complains.
     *
     * @param id the id of the complaint
     * @param complaint the text of the complaint
     * @param mecanographicNumber the mecanographic number of the user who
     * complains
     */
    public Complaint(String complaint, MecanographicNumber mecanographicNumber) {
        this.id = new ComplaintId();
        if (Strings.isNullOrEmpty(complaint) || Strings.isNullOrWhiteSpace(complaint)) {
            throw new IllegalStateException("The complaint must contain some sort of text!");
        }
        this.complaint = complaint;
        if (mecanographicNumber == null) {
            throw new IllegalStateException("The mecanographic number can't be null!");
        }
        this.mecanographicNumber = mecanographicNumber;
    }

    /**
     * Complaint constructor complete.
     *
     * @param id the id of the complaint
     * @param complaint the text of the complaint
     * @param dish the dish of the complaint
     * @param mecanographicNumber the mecanographic number of the user who
     * complains
     */
    public Complaint(String complaint, Dish dish, MecanographicNumber mecanographicNumber) {
        this.id = new ComplaintId();
        if (Strings.isNullOrEmpty(complaint) || Strings.isNullOrWhiteSpace(complaint)) {
            throw new IllegalStateException("The complaint must contain some sort of text!");
        }
        this.complaint = complaint;
        if (dish == null) {
            throw new IllegalStateException("The dish can't be null!");
        }
        this.dish = dish;
        if (mecanographicNumber == null) {
            throw new IllegalStateException("The mecanographic number can't be null!");
        }
        this.mecanographicNumber = mecanographicNumber;
    }

    /**
     * Protect Construtor for ORM
     */
    protected Complaint() {
        // for ORM
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Complaint obj = (Complaint) other;
        if (!Objects.equals(this.complaint, obj.complaint)) {
            return false;
        }
        return Objects.equals(this.id, obj.id);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean is(ComplaintId id) {
        return this.id.equals(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplaintId id() {
        return this.id;
    }

}
