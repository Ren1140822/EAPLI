/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.ecafeteria.Application;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * TODO javadoc
 *
 * @author arocha
 */
@Entity
public class OrganicUnit implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String MECANOGRAPHIC_NUMBER_VALIDATION_PROPERTY = "domain.mecanographicNumberStrategy.";

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @Column(unique = true)
    private String acronym;
    private String name;
    private String description;
    private boolean active;

    protected OrganicUnit() {
        // for ORM
    }

    public OrganicUnit(String acronym, String name, String description) {
        if (Strings.isNullOrWhiteSpace(acronym) || Strings.isNullOrWhiteSpace(name) || Strings.isNullOrWhiteSpace(description)) {
            throw new IllegalStateException();
        }
        this.acronym = acronym;
        this.name = name;
        this.description = description;
        this.active = true;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String acronym) {
        return acronym.equalsIgnoreCase(this.acronym);
    }

    public boolean isActive() {
        return this.active;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    /**
     * It builds the right class which implements this organic units validations.
     * 
     * @return It returns right Mecanographic Number Validation object.
     */
    private MecanographicNumberStrategy buildMecanographicNumberValidator() {
        final String strategyName = Application.settings().getMecanographicNumberValidation(MECANOGRAPHIC_NUMBER_VALIDATION_PROPERTY + acronym);
        try {
            return (MecanographicNumberStrategy) Class.forName(strategyName).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NullPointerException ex) {
            Logger.getLogger(MecanographicNumber.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException("No mecanographic number validation strategy found.");
        }
    }

    /**
     * It provides the intructions of the organic unit's mecanographic rules.
     *
     * @return It returns the Mecanographic Number validation instructions..
     */
    public String mecanographicNumberInstructions() {
        return buildMecanographicNumberValidator().instructions();
    }

    /**
     * It checks the mecanographic number compliance with the organic unit
     * rules.
     *
     * @param number The mecanographic number to be validated.
     * @return It returns "true" if the number is valid or "false" otherwise.
     */
    public boolean validateMecanographicNumber(MecanographicNumber number) {
        return number.compliesWith(buildMecanographicNumberValidator());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganicUnit)) {
            return false;
        }

        final OrganicUnit other = (OrganicUnit) o;
        return this.id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof OrganicUnit)) {
            return false;
        }

        final OrganicUnit that = (OrganicUnit) other;
        if (this == that) {
            return true;
        }

        return this.acronym.equals(that.acronym) && name.equals(that.name) && description.equals(that.description)
                && active == that.active;
    }
}
