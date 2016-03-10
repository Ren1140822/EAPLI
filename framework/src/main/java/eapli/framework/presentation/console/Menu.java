/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.presentation.console;

import eapli.util.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class Menu {

    private String title;
    private List<MenuItem> itens = new ArrayList<MenuItem>();
    private Map<Integer, MenuItem> itemByOption = new HashMap<Integer, MenuItem>();

    public Menu() {
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

    public boolean show() {
        for (MenuItem item : itens) {
            item.show();
        }

        MenuItem item;
        do {
            int option = Console.readInteger("Please choose an option");
            item = itemByOption.get(option);
        } while (item == null);
        return item.select();
    }

    public String title() {
        return title;
    }
}
