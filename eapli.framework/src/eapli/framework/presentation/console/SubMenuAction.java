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
public class SubMenuAction implements Action {

    private Menu menu;

    public SubMenuAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean execute() {
        System.out.println("\n>> " + menu.title());
        menu.show();
        return false;
    }
}
