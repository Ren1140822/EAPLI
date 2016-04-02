package eapli.ecafeteria.utente.consoleapp.presentation.ui;

import eapli.util.Console;

/**
 * widget for reading user data Jorge Santos ajs@isp.ipp.pt
 */
public class UserDataWidget {

    final String username;
    final String password;
    final String firstName;
    final String lastName;
    final String email;

    protected UserDataWidget() {

        username = Console.readLine("Username");
        password = Console.readLine("Password");
        firstName = Console.readLine("First Name");
        lastName = Console.readLine("Last Name");
        email = Console.readLine("E-Mail");
    }
}
