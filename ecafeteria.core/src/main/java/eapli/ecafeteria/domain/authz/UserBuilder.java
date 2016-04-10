package eapli.ecafeteria.domain.authz;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import eapli.framework.domain.Factory;
import eapli.util.Strings;
import java.util.stream.Collectors;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 */
public class UserBuilder implements Factory<SystemUser> {

    private Username username;
    private Password password;

    private String firstName;
    private String lastName;

    private Name name;
    private EmailAddress email;

    private final RoleSet roles;

    private Calendar createdOn;

    public UserBuilder() {
        roles = new RoleSet();
    }

    public UserBuilder withUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    public UserBuilder withUsername(Username username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = new Password(password);
        return this;
    }

    public UserBuilder withPassword(Password password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        if (!Strings.isNullOrEmpty(lastName)) {
            this.name = new Name(this.firstName, this.lastName);
        }
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        if (!Strings.isNullOrEmpty(firstName)) {
            this.name = new Name(this.firstName, this.lastName);
        }
        return this;
    }

    public UserBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = new EmailAddress(email);
        return this;
    }

    public UserBuilder withEmail(EmailAddress email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRole(RoleType role) {
        this.roles.add(new Role(role));
        return this;
    }

    public UserBuilder withRole(Role role) {
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
            return new SystemUser(this.username, this.password, this.name, this.email, this.roles,
                    this.createdOn);
        } else {
            return new SystemUser(this.username, this.password, this.name, this.email, this.roles);
        }
    }

    public UserBuilder withRoles(Set<RoleType> roles) {
        this.roles.addAll(roles.stream().map(rt -> new Role(rt, this.createdOn)).collect(Collectors.toList()));
        return this;
    }

    public UserBuilder withRoles(RoleSet roles) {
        this.roles.addAll(roles);
        return this;
    }

}
