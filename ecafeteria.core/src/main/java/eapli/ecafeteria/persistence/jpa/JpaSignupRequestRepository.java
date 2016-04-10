package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends JpaRepository<SignupRequest, Username> implements SignupRequestRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public Iterable<SignupRequest> listSignupRequestsPending() {
        
      ArrayList listOfPending = new ArrayList();
      
        for (SignupRequest obj : this.all()) {
            if (obj.isPending())
                listOfPending.add(obj);
        }

            return listOfPending;
    }

}
