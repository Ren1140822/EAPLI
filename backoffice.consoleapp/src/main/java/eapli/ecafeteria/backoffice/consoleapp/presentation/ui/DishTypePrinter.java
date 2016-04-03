/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.domain.DishType;
import eapli.framework.visitor.Visitor;

/**
 * @author pgsou_000
 *
 */
class DishTypePrinter implements Visitor<DishType> {

    /**
     *
     */
    public DishTypePrinter() {
    }

    @Override
    public void visit(DishType visitee) {
        System.out.printf("%-10s%-30s%-4s\n", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(DishType visitee) {
    }

    @Override
    public void afterVisiting(DishType visitee) {
    }
}
