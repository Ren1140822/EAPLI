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



@Embeddable
public class MealType implements ValueObject, Serializable {

    public enum MealTypes { ALMOCO, JANTAR };

    private MealTypes mealType;

    protected MealType() {} // For ORM

    public MealType(MealTypes mealType) {
        if(mealType == null){
            throw new IllegalStateException();
        }
        this.mealType = mealType;
    }

    public String mealType() {
        return this.mealType.name();
    }
}
