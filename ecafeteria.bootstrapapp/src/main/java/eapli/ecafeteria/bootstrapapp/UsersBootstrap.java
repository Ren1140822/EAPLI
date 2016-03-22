/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapapp;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.UserRegisterController;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.framework.actions.Action;

/**
 * @author pgsou_000
 */
public class UsersBootstrap implements Action {

	@Override
	public boolean execute() {

		// Username username = new Username("admin");
		// Password password = new Password("admin");

		registerAdmin();
		return false;
	}

	/**
	 * 
	 */
	private void registerAdmin() {
		final String username = "admin";
		final String password = "admin";

		final String firstName = "John";
		final String lastName = "Doe";

		final String email = "john.doe@emai.l.com";

		final List<RoleType> roles = new ArrayList<RoleType>();
		roles.add(RoleType.Admin);

		final UserRegisterController userController = new UserRegisterController();
		userController.registerUser(username, password, firstName, lastName, email, roles);
	}
}
