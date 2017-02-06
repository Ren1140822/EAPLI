package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends CafeteriaJpaRepositoryBase<SignupRequest, Username>
        implements SignupRequestRepository {

    @Override
    public Iterable<SignupRequest> pendingSignupRequests() {
        return match("e.approvalStatus=eapli.ecafeteria.domain.cafeteria.ApprovalStatus.PENDING");
    }
}
