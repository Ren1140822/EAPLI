/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import eapli.framework.actions.NullAction;

/**
 * A separator for vertical menus.
 *
 * @author Paulo Gandra Sousa
 */
public class VerticalSeparator extends MenuItem {

    private static final VerticalSeparator instance = new VerticalSeparator();

    public static VerticalSeparator separator() {
        return instance;
    }

    public VerticalSeparator() {
        super(-1, "------------------", NullAction.instance());
    }

    @Override
    public void show() {
        System.out.print(text());
    }
}
