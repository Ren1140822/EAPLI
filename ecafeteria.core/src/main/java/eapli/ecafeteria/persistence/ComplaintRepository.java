/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.cashregister.Complaint;
import eapli.ecafeteria.domain.cafeteria.cashregister.ComplaintId;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public interface ComplaintRepository extends DataRepository<Complaint, ComplaintId>{
    
}
