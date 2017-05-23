package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterLotsInMealController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.MealPrinter;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;

/**
 * @author Pedro Fernandes (1060503@isep.ipp.pt) Diana Silva (1151088@isep.ipp.pt)
 */
public class RegisterLotsInMealUI extends AbstractUI {
 
     private final RegisterLotsInMealController theController= new RegisterLotsInMealController();

     protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {
  
       
        final Iterable<Meal> meals=this.theController.showMealsOfDay();
        final Iterable<Material> materials=this.theController.showMaterials();
        
        if(!meals.iterator().hasNext()){
            System.out.println("There are no registered meals for the current day!");
        }
        else if(!materials.iterator().hasNext()){
            System.out.println("There are no registered raw materials!");

        }else{
     
            //SELECTION OF MEAL
            final SelectWidget<Meal> selectorMeal=new SelectWidget<>("Meals: ", meals, new MealPrinter());
            selectorMeal.show();
             Meal selectedMeal=selectorMeal.selectedElement();
            
            if(selectedMeal==null){
                return false;
            }
            
            boolean cont=true;
            while(cont==true){
                //SELECTION OF MATERIAL
                final SelectWidget<Material> selectorMaterial=new SelectWidget<>("Materials: ", materials, new MaterialPrinter());
                selectorMaterial.show();
                Material selectedMaterial=selectorMaterial.selectedElement();

                if(selectedMaterial==null){
                    return false;
                }

                //SELECTION OF LOT
                final String lot_code= Console.readLine(("Lot code: "));

                try {
                    this.theController.registerMaterialUsed(selectedMeal, selectedMaterial, lot_code);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("It wasn´t possible to register lot in meal.");

                }
                
                cont= Console.readBoolean("Do you want to insert another material? (y/n)");
            }
        }
        System.out.println("Lot code registered in meal successfully");
        
        return true; 
    }

    @Override
    public String headline() {
        return "Register Lots in Meal";
    }
}