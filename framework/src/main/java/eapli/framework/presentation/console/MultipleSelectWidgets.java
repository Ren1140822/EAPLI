package eapli.framework.presentation.console;

import eapli.framework.visitor.Visitor;
import eapli.util.io.Console;
import java.util.Collection;
import java.util.Iterator;

/**
 * A simple widget that lists the items of a collection and allows the user to
 * select an item.
 *
 * @author Paulo Gandra Sousa
 * @param <T> the type of element in the collection
 */
public class MultipleSelectWidgets<T> extends SelectWidget<T>{

     public MultipleSelectWidgets(Collection<T> source) {
        super(source);
    }

    public MultipleSelectWidgets(Iterable<T> source) {
        super(source);
    }

    public MultipleSelectWidgets(Collection<T> source, Visitor<T> visitor) {
        super(source, visitor);
    }

    public MultipleSelectWidgets(Iterable<T> source, Visitor<T> visitor) {
        super(source, visitor);
    }
    
    
}
