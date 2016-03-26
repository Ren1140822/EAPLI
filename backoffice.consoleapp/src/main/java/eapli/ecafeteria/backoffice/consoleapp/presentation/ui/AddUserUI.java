package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.AddUserController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.util.AddRoleType2List;
import eapli.cafeteria.consoleapp.presentation.visitors.UserUIVisitor;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.framework.actions.ReturnAction;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.util.Console;

/**
 * UI for adding a user to the application. Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {
	private final AddUserController theController = new AddUserController();

	@Override
	protected Controller controller() {
		return theController;
	}

	@Override
	protected boolean doShow() {

		final String username = Console.readLine("Username");
		final String password = Console.readLine("Password");
		final String firstName = Console.readLine("First Name");
		final String lastName = Console.readLine("Last Name");
		final String email = Console.readLine("E-Mail");
		final List<RoleType> roleTypes = new ArrayList<>();

		boolean show;
		do {
			show = showRoles(roleTypes);
		} while (!show);

		User user = theController.addUser(username, password, firstName, lastName, email, roleTypes);

		UserUIVisitor visitor = new UserUIVisitor();
		user.accept(visitor);

		return false;
	}

	private boolean showRoles(List<RoleType> roleTypes) {
		// TODO we could also use the "widget" classes from the framework...
		final Menu rolesMenu = buildRolesMenu(roleTypes);
		final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu);
		return renderer.show();
	}

	private Menu buildRolesMenu(List<RoleType> roleTypes) {
		final Menu rolesMenu = new Menu();

		int counter = 0;

		rolesMenu.add(new MenuItem(counter++, "No Role", new ReturnAction()));

		for (final RoleType roleType : getRoleTypes()) {
			// System.out.println(roleType);
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
		// TODO NMB: Should this method have direct access to RoleTypes?
		return RoleType.values();
	}

	@Override
	public String headline() {
		return "Add User";
	}

}
