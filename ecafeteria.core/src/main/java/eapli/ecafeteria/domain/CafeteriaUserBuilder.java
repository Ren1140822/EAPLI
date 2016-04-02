package eapli.ecafeteria.domain;

import eapli.ecafeteria.domain.users.*;
import java.util.Calendar;
import java.util.List;

public class CafeteriaUserBuilder {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleType> roles;
    private Calendar createdOn;

    public CafeteriaUserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public CafeteriaUserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CafeteriaUserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CafeteriaUserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CafeteriaUserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CafeteriaUserBuilder setRoles(List<RoleType> roles) {
        this.roles = roles;
        return this;
    }

    public SystemUser createUser() {
        if (createdOn != null) {
            return new SystemUser(username, password, firstName, lastName, email, roles, createdOn);
        } else {
            return new SystemUser(username, password, firstName, lastName, email, roles);
        }
    }

}
