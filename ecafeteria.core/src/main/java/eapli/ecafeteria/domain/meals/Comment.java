/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate?
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Embeddable
public class Comment implements Serializable {

    private static final Integer NUMBER_CHARACTERS_LIMIT = 240;
    private String comment;

    protected Comment() {
    }

    public Comment(String comment) {
        if (comment.length() > NUMBER_CHARACTERS_LIMIT) {
            throw new IllegalStateException("Comment is too long.");
        }
        this.comment = comment;
    }

}
