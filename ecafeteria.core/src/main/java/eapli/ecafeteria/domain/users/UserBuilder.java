package eapli.ecafeteria.domain.users;

import java.util.List;

public class UserBuilder {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleType> roles;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setRoles(List<RoleType> roles) {
        this.roles = roles;
        return this;
    }

    public User createUser() {
        return new User(username, password, firstName, lastName, email, roles);
    }
}