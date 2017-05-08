package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 * @FIXME javadoc
 *
 */
@Entity
public class Allergen implements AggregateRoot<Designation>, Serializable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @Column(unique = true)
    private Designation name;
    private String description;

    public Allergen(Designation name, String description) {
        if (name == null | description == null) {
            throw new IllegalStateException("Allergen name or description cannot be null");
        }
        this.name = name;
        this.description = description;
    }

    protected Allergen() {
        // for ORM only
    }

    public Designation name() {
        return name;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean is(Designation id) {
        return id.equals(name);
    }

    @Override
    public Designation id() {
        return name;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Allergen)) {
            return false;
        }

        final Allergen that = (Allergen) other;
        if (this == that) {
            return true;
        }

        return (id().equals(that.id()) && description().equals(that.description()));
    }
}
