/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.Collection;

import eapli.framework.visitor.Visitor;
import eapli.util.Console;

/**
 * a simple widget that lists the itens of a collection and allows the user to
 * select an item.
 * 
 * @author Paulo Gandra Sousa
 * @param <T>
 */
public class SelectWidget<T> extends ListWidget<T> {

	private int option = -1;

	public SelectWidget(Collection<T> source, Visitor<T> visitor) {
		super(source, visitor);
	}

	@Override
	public void show() {
		super.show();
		System.out.println("0. Exit");
		option = Console.readOption(1, size(), 0);
	}

	/**
	 *
	 * @return -1 is the user has not yet made a selection 0 if the user
	 *         selected "exit" a positive number corresponding to the list index
	 *         os source if the user selected an item
	 */
	public int selectedOption() {
		return option;
	}
}
