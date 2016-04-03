package eapli.ecafeteria.utente.consoleapp.visitors;

import eapli.framework.dto.GenericDTO;
import eapli.framework.visitor.Visitor;

/**
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class OrganicUnitUIVisitor implements Visitor<GenericDTO> {

    @Override
    public void visit(GenericDTO visited) {
         System.out.println(visited.get("id") +  " " + visited.get("acronym") + " " + visited.get("description") +   " " + visited.get("active"));
      
    }

    @Override
    public void beforeVisiting(GenericDTO visited) {
        System.out.println("ID Acronimo Description Active");
    }

    @Override
    public void afterVisiting(GenericDTO visited) {

    }
}
