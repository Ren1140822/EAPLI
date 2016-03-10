/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.actions.Action;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class MenuItem {

    private int option;
    private String text;
    private Action action;

    public MenuItem(int option, String text, Action action) {
        if (text == null || action == null) {
            throw new IllegalArgumentException();
        }
        this.option = option;
        this.text = text;
        this.action = action;
    }

    public boolean select() {
        return action.execute();
    }

    public void show() {
        System.out.print(option + ". ");
        System.out.println(text());
    }

    /**
     * @return the text
     */
    public String text() {
        return text;
    }

    public int option() {
        return option;
    }
}
