/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteria.cashregister.Complaint;
import eapli.ecafeteria.domain.cafeteria.cashregister.ComplaintId;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class InMemoryComplaintRepository extends InMemoryRepository<Complaint, ComplaintId>  implements ComplaintRepository{

    @Override
    protected ComplaintId newPK(Complaint c) {
        return c.id();
    }
    
}
