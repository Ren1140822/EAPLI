/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class Menu {

	private final String				 title;
	private final List<MenuItem>		 itens		  = new ArrayList<MenuItem>();
	private final Map<Integer, MenuItem> itemByOption = new HashMap<Integer, MenuItem>();

	public Menu() {
		title = "";
	}

	public Menu(String title) {
		this.title = title;
	}

	public void add(MenuItem item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		itens.add(item);
		itemByOption.put(item.option(), item);
	}

	public String title() {
		return title;
	}

	public Iterable<MenuItem> itens() {
		return itens;
	}

	public MenuItem item(int option) {
		return itemByOption.get(option);
	}
}
