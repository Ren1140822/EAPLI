/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.domain.OrganicUnit;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author arocha
 */
public class AddOrganicUnitController implements Controller {
    public OrganicUnit addOrganicUnit(String acronym, String name, String description)
            throws DataIntegrityViolationException {

        if (!AppSettings.instance().session().authenticatedUser().isAuthorizedTo(ActionRight.Administer)) {
            // TODO check which exception to throw
            throw new IllegalStateException("user is not authorized to perform this action");
        }

        final OrganicUnit newOrganicUnit = new OrganicUnit(acronym, name, description);
        final OrganicUnitRepository organicUnitRepository = PersistenceContext.repositories().organicUnits();
        // TODO error checking if the acronym is already in the persistence
        // store
        organicUnitRepository.add(newOrganicUnit);
        return newOrganicUnit;

    }
}