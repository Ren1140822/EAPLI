package eapli.ecafeteria.backoffice.presentation;

import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.visitors.UserUIVisitor;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.framework.actions.ReturnAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.*;
import eapli.util.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * UI for adding a user to the application.
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {
	private final AddUserController theController = new AddUserController();

	@Override
	protected Controller controller() {
		return theController;
	}

	@Override
	protected boolean doShow() {

		String username = Console.readLine("Username");
		String password = Console.readLine("Password");
		String firstName = Console.readLine("First Name");
		String lastName = Console.readLine("Last Name");
		String email = Console.readLine("E-Mail");
		List<RoleType> roleTypes = new ArrayList<>();

		boolean show;
		do {
			show = showRoles(roleTypes);
		}
		while (!show);

		User user = theController.addUser(username, password, firstName, lastName, email, roleTypes);

		UserUIVisitor visitor = new UserUIVisitor();
		user.accept(visitor);


		return false;
	}

	private boolean showRoles(List<RoleType> roleTypes) {
		Menu rolesMenu = buildRolesMenu(roleTypes);
		final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu);
		return renderer.show();
	}

	private Menu buildRolesMenu(List<RoleType> roleTypes) {
		Menu rolesMenu = new Menu();

		int counter = 0;

		rolesMenu.add(new MenuItem(counter++, "No Role", new ReturnAction()));

		for (RoleType roleType : getRoleTypes()) {
			System.out.println(roleType);
			rolesMenu.add(new MenuItem(counter++, roleType.name(), new AddRoleType2List(roleTypes, roleType)));
		}
		return rolesMenu;
	}


	/**
	 * Get all the existing RoleTypes.
	 *
	 * @return a list of RoleTypes
	 */
	private RoleType[] getRoleTypes() {
		//TODO NMB: Should this method have direct access to RoleTypes?
		return RoleType.values();
	}

	@Override
	public String headline() {
		return "Add User";
	}


}
