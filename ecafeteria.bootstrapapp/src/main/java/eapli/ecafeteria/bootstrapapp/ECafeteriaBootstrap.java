package eapli.ecafeteria.bootstrapapp;

import eapli.framework.actions.Action;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrap implements Action {

	public static void main(String[] args) {
		System.out.println("Bootstrapping eCafeteria 2016(c) data");

		new ECafeteriaBootstrap().execute();
	}

	@Override
	public boolean execute() {
		// declare bootstrap actions
		final Action[] actions = { new UsersBootstrap(), };

		// execute all bootstrapping returning true if any of the bootstraping
		// actions returns true
		boolean ret = false;
		for (final Action boot : actions) {
			ret |= boot.execute();
		}
		return ret;
	}
}
