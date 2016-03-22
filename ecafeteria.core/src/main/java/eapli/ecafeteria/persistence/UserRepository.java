package eapli.ecafeteria.persistence;


import eapli.ecafeteria.domain.users.User;
import eapli.ecafeteria.domain.users.Username;
import eapli.framework.persistence.repositories.Repository;

/**
 * Created by nuno on 21/03/16.
 */
public interface UserRepository extends Repository<User, Username> {
}
