package eapli.ecafeteria.bootstrapapp;

import eapli.framework.actions.Action;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap {

	public static void main(String[] args) {
		System.out.println("Bootstrapping eCafeteria 2016(c) data");
		bootstrap();
	}

	public static void bootstrap() {
		// declare bootstrap actions
		final Action[] actions = { new UsersBootstrap(), };

		// execute all bootstrapping
		for (final Action boot : actions) {
			boot.execute();
		}
	}
}
