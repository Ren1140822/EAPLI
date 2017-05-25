/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MaterialUsed;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MaterialUsedRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 *
 * @author João
 */
public class ListMaterialService {
    
    private final MaterialUsedRepository repo = PersistenceContext.repositories().materialUsed();
    
    
    
      public Iterable<MaterialUsed> searchMaterialsUsedByLot(String lotCode) {
       return repo.searchByLot(lotCode);
        
    }
//        public Iterable<Meal> showMealInMaterialsUsedByLot(String lotCode){
//        Iterable<MaterialUsed> materialsUsedList = searchMaterialsUsedByLot(lotCode);
//        ArrayList<Meal> meals = new ArrayList<>();
//        for (MaterialUsed materialUsed : materialsUsedList) {
//            meals.add(materialUsed.meal());
//        }
//        
//        return meals;
    }       
    
