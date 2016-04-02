package eapli.ecafeteria.domain.authz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.framework.domain.Factory;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class UserBuilder implements Factory<SystemUser> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private final List<RoleType> roles = new ArrayList<>();
    private Calendar createdOn;

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRole(RoleType role) {
        this.roles.add(role);
        return this;
    }

    public UserBuilder withCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public SystemUser build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        if (this.createdOn != null) {
            return new SystemUser(this.username, this.password, this.firstName, this.lastName, this.email, this.roles,
                    this.createdOn);
        } else {
            return new SystemUser(this.username, this.password, this.firstName, this.lastName, this.email, this.roles);
        }
    }

    public UserBuilder withRoles(List<RoleType> roles) {
        this.roles.addAll(roles);
        return this;
    }
}
