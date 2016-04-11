package eapli.ecafeteria.utente.consoleapp.presentation.authz;

import eapli.util.Console;

/**
 * TODO move to console.common to allow reuse from both backoffice and UtenteApp
 *
 * widget for reading user data Jorge Santos ajs@isp.ipp.pt
 */
class UserDataWidget {

    // FIXME attributes should be private
    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    public void show() {
        this.username = Console.readLine("Username");
        this.password = Console.readLine("Password");
        this.firstName = Console.readLine("First Name");
        this.lastName = Console.readLine("Last Name");
        this.email = Console.readLine("E-Mail");
    }
}
