package eapli.ecafeteria.backoffice.consoleapp.presentation.util;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.framework.actions.Action;

import java.util.List;

/**
 * Action that allows adding a RoleType to a RoleType List.
 * Created by nuno on 22/03/16.
 */
public class AddRoleType2List implements Action {

    private List<RoleType> roleTypes;
    private final RoleType roleType;

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
