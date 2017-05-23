package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MCN on 29/03/2016.
 */
class JpaMaterialRepository extends CafeteriaJpaRepositoryBase<Material, Long> implements MaterialRepository {

    @Override
    public Material findByAcronym(String acronym) {
	    Map<String, Object> params = new HashMap();
	    params.put("acronym", acronym);
        List<Material> ret = match("e.acronym=:acronym", params);
        return ret.size() > 0 ? ret.get(0) : null;
    }
}
