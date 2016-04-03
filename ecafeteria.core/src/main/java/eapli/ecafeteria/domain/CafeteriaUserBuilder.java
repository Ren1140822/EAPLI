package eapli.ecafeteria.domain;



import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.Factory;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class CafeteriaUserBuilder implements Factory<CafeteriaUser> {

    private SystemUser systemUser;
    private String account;
    private OrganicUnit organicUnit;
    private String mecanographicNumber;
    private Status status;

    public CafeteriaUserBuilder withSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public CafeteriaUserBuilder withAccount(String account) {
        this.account = account;
        return this;
    }

    public CafeteriaUserBuilder withOrganicUnit(OrganicUnit organicUnit) {
        this.organicUnit = organicUnit;
        return this;
    }

    public CafeteriaUserBuilder withMecanographicNumber(String mecanographicNumber) {
        this.mecanographicNumber = mecanographicNumber;
        return this;
    }

    public CafeteriaUserBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public CafeteriaUser build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor

        return new CafeteriaUser(this.systemUser, this.account, this.organicUnit, this.mecanographicNumber);
    }

}
