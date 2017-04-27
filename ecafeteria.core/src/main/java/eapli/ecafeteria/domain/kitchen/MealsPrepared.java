/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author Diogo Santos [1150451@isep.ipp.pt] Sofia Silva [1150690@isep.ipp.pt]
 */
@Entity
public class MealsPrepared implements Serializable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Long version;
    
    /*
    *
    */
    private Meal meal;
    
    /**
     * The quantity of prepared meals.
     */
    private int quantity;
    
    /**
     * 
     */
    @OneToMany (mappedBy = "MealsPrepared", cascade = CascadeType.ALL)
    private List<MaterialUsed> usedMaterials;

    
    protected MealsPrepared() {
        // for ORM
    }
    

    /**
     * Prepared Meals Constructor.
     *
     * @param quantity the quantity of prepared meals
     */
    public MealsPrepared(Meal meal, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity of prepared meals must be a non negative number.");
        }
        this.quantity = quantity;
        if(meal == null){
            throw new IllegalArgumentException("Meals prepared must contain a meal.");
        }
        this.meal = meal;
        this.usedMaterials = new ArrayList<>();
    }

    /**
     * 
     * @param usedMaterial a material used in a meal to add
     * @return 
     */
    public boolean addMaterialsUsed(MaterialUsed usedMaterial){
        return this.usedMaterials.add(usedMaterial);
    }
    

}
