/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Diogo Santos
 */

@Embeddable
public class PreparedMeals implements ValueObject, Serializable{
    
    	// ORM primary key
	@Id
	@GeneratedValue
	private Long pk;
	@Version
	private Long version;
        
        private int quantity;

    public PreparedMeals(int quantity) {
        if (quantity<0){
            throw new IllegalArgumentException("Quantity must be a non negative number.");
        }
        this.quantity = quantity;
    }
        
        
}
