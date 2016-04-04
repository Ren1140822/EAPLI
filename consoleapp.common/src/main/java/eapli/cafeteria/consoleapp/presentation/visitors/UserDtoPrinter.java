package eapli.cafeteria.consoleapp.presentation.visitors;

import eapli.framework.dto.GenericDTO;
import eapli.framework.visitor.Visitor;

/**
 * Created by Nuno Bettencourt [NMB] on 26/03/16.
 */
public class UserDtoPrinter implements Visitor<GenericDTO> {
    @Override
    public void visit(GenericDTO visited) {
        // TODO: Discussion: Is not this type of implementation worse than using
        // getter?
        // This way it may fail during runtime
        System.out.println("UserName:" + visited.get("username"));
        System.out.println("Name:" + visited.get("name"));
        System.out.println("Email:" + visited.get("email"));
        System.out.println("Roles:" + visited.get("roles"));
    }

    @Override
    public void beforeVisiting(GenericDTO visited) {
        // nothing to do
    }

    @Override
    public void afterVisiting(GenericDTO visited) {
        // nothing to do
    }
}
