/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

import java.util.Calendar;

/**
 *
 * @author Ricardo Catalao <1150385@isep.ipp.pt>
 */
public class ListOrganicUnitsService {
    
    private final OrganicUnitRepository organicUnitRepository= PersistenceContext.repositories().organicUnits();
    
    public Iterable<OrganicUnit> listOrganicUnits() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.organicUnitRepository.findAll();
    }
}
