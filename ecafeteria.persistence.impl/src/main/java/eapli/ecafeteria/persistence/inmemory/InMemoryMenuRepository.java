/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Eduangelo Ferreira
 */
public class InMemoryMenuRepository extends InMemoryRepositoryWithLongPK<Menu> implements MenuRepository {

    @Override
    public Menu findByPk(Long pk) {
        return matchOne(e->e.pk().equals(pk));
    }

    @Override
    public Iterable<Menu> publishedMenu() {
        return match(e -> e.isPublished());
    }

    @Override
    public Iterable<Menu> notPublishedMenu() {
        return match(e->!e.isPublished());
    }

}
