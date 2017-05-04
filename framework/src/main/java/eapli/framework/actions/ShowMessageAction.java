/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.actions;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ShowMessageAction implements Action {

    private final String message;

    public ShowMessageAction(String message) {
	this.message = message;
    }

    @Override
    @SuppressWarnings("squid:S106")
    public boolean execute() {
	System.out.println(this.message);
	return true;
    }
}
