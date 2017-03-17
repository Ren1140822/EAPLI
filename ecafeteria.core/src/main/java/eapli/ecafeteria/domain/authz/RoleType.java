package eapli.ecafeteria.domain.authz;

public enum RoleType {
    User, // utente
    Admin,
    KitchenManager,
    MenuManager,
    Cashier;

    /**
     * get available role types for user adding
     *
     * @return
     */
    public static RoleType[] nonUserValues() {
        final RoleType[] roleTypes = new RoleType[RoleType.values().length - 1];
        int idx = 0;
        for (final RoleType roleType : RoleType.values()) {
            if (roleType != RoleType.User) {
                roleTypes[idx++] = roleType;
            }
        }
        return roleTypes;
    }
}
