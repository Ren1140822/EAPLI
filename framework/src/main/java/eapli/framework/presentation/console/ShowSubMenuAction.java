/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.actions.Action;

/**
 *
 * @author Meireles
 */
public class ShowSubMenuAction implements Action {

    private final Menu menu;
    private final MenuRenderer renderer;

    public ShowSubMenuAction(Menu menu, MenuRenderer renderer) {
	this.menu = menu;
	this.renderer = renderer;
    }

    @Override
    public boolean execute() {
	System.out.println("\n>> " + this.menu.title());
	this.renderer.show();
	return false;
    }
}
