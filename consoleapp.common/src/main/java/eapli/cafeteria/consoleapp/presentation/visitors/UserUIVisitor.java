package eapli.cafeteria.consoleapp.presentation.visitors;

import eapli.framework.dto.GenericDTO;
import eapli.framework.visitor.Visitor;

/**
 * Created by Nuno Bettencourt [NMB] on 26/03/16.
 */
public class UserUIVisitor implements Visitor<GenericDTO> {
	@Override
	public void visit(GenericDTO visited) {
		//TODO: Ask esta implementação não é pior do que usar getters? pois pode falhar em runtime?
		System.out.println("UserName:" + visited.get("username"));
		System.out.println("Name:" + visited.get("name"));
		System.out.println("Email:" + visited.get("email"));
		System.out.println("Roles:" + visited.get("roles"));
	}

	@Override
	public void beforeVisiting(GenericDTO visited) {

	}

	@Override
	public void afterVisiting(GenericDTO visited) {

	}
}