package eapli.ecafeteria.utente.consoleapp.visitors;

import eapli.ecafeteria.domain.OrganicUnit;
import eapli.framework.visitor.Visitor;

/**
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class OrganicUnitUIVisitor implements Visitor<OrganicUnit> {

    @Override
    public void visit(OrganicUnit visited) {
        System.out.println(visited.id() + " " + visited.description() + " " + visited.isActive());

    }

    @Override
    public void beforeVisiting(OrganicUnit visited) {
        System.out.println("ID Acronimo Description Active");
    }

    @Override
    public void afterVisiting(OrganicUnit visited) {

    }
}
