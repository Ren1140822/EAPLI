/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.*;



@Entity
public class MealType implements ValueObject, Serializable {

    private static final long serailVersionUID = 1L;

    @Id
    private String mealType;
    @Version
    private Long version;

    protected MealType() {} //for ORM

    public MealType(String mealType) {
        if (Strings.isNullOrEmpty(mealType)) {
            throw new IllegalArgumentException();
        }
        this.mealType = mealType;
    }

    public String mealType() {
        return this.mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealType mealType1 = (MealType) o;

        return mealType.equals(mealType1.mealType);
    }

    @Override
    public int hashCode() {
        return mealType.hashCode();
    }
}
