package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.ActivateDeactivateDishTypeController;
import eapli.ecafeteria.domain.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeUI extends AbstractUI {

	private final ActivateDeactivateDishTypeController theController = new ActivateDeactivateDishTypeController();

	@Override
	protected Controller controller() {
		return this.theController;
	}

	@Override
	protected boolean doShow() {
		final List<DishType> list = new ArrayList<>();
		final Iterable<DishType> iter = this.theController.listDishTypes();
		if (!iter.iterator().hasNext()) {
			System.out.println("There is no registered Dish Type");
		} else {
			int option;
			int cont = 1;
			System.out.println("SELECT Dish Type to Activate / Deactivate\n");
			System.out.printf("%-6s%-10s%-30s%-6s\n", "Nº:", "Acronym", "Description", "Active");
			for (final DishType dT : iter) {
				list.add(dT);
				System.out.printf("%-6d%-10s%-30s%-4s\n", cont, dT.id(), dT.description(), String.valueOf(dT.isActive()));
				cont++;
			}
			switch (option = Console.readInteger("Enter dish nº to change ative state or 0 to finish ")) {
				case 0:
					System.out.println("No dish type selected");
					break;
				default:
					this.theController.changeDishTypeState(list.get(option - 1));
			}
		}
		return true;
	}

	@Override
	public String headline() {
		return "Activate / Deactivate Dish Types";
	}
}
