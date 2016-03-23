package eapli.ecafeteria.backoffice.presentation;

import eapli.ecafeteria.domain.users.Role;
import eapli.ecafeteria.domain.users.RoleType;
import eapli.ecafeteria.domain.users.User;
import eapli.framework.actions.Action;

import java.util.List;

/**
 * Action that allows adding a RoleType to a RoleType List.
 * Created by nuno on 22/03/16.
 */
public class AddRoleType2List implements Action {

    private List<RoleType> roleTypes;
    private RoleType roleType;

    public AddRoleType2List(List<RoleType> roleTypes, RoleType roleType) {
        this.roleType = roleType;
        this.roleTypes = roleTypes;
    }

    @Override
    public boolean execute() {
        //TODO: ASK if the role has already been added, how to give user feedback?
        if (!roleTypes.contains(roleType)) {
            roleTypes.add(roleType);
        }
        return false;
    }
}
