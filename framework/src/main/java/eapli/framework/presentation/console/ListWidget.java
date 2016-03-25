/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.presentation.console;

import java.util.Collection;

import eapli.framework.visitor.Visitor;

/**
 * a simple widget to list the itens of a collection
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 */
public class ListWidget<T> {

	private final Collection<T>	source;
	private final Visitor<T>	visitor;

	public ListWidget(Collection<T> source, Visitor<T> visitor) {
		this.source = source;
		this.visitor = visitor;
	}

	public void show() {
		int position = 0;
		for (final T et : source) {
			position++;
			System.out.print(position + ". ");
			visitor.visit(et);
		}
	}

	protected int size() {
		return source.size();
	}
}
