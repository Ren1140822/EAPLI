/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.*;
import eapli.ecafeteria.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduangelo Ferreira
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, Long> implements MenuRepository {

    @Override
    public Menu findByPk(Long pk) {
        return matchOne("e.pk=pk", "pk", pk);
    }

    @Override
    public Iterable<Menu> publishedMenu() {
        return match("e.published=true");
    }

    @Override
    public Iterable<Menu> notPublishedMenu() {
       return match("e.published=false");
    }

    @Override
    public Iterable<Menu> publishedMenusOfDay(Calendar day) {
        Map<String, Object> params = new HashMap<>();
        params.put("day",day);
       return match("e.period.start<=:day and e.period.end>=:day",params );
    }

}
