/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.ValueObject;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * It represents the Comment.
 *
 * @author Sofia Gonçalves [1150657@isep.ipp.pt] Pedro Chilro
 * [1150019@isep.ipp.pt]
 */
@Embeddable
public class Comment implements ValueObject{

    /**
     * The limit of characters that the comment could reach.
     */
    private static final Integer NUMBER_CHARACTERS_LIMIT = 240;

    /**
     * The comment of Comment.
     */
    private String comment;

    protected Comment() {
        //for ORM
    }

    /**
     * The constructor of Comment.
     *
     * @param comment The comment that can't be greater than the
     * NUMBER_CHARACTERS_LIMIT.
     */
    public Comment(String comment) {
        if (comment.length() > NUMBER_CHARACTERS_LIMIT) {
            throw new IllegalStateException("Comment is too long.");
        }
        this.comment = comment;
    }

    /**
     * The override method of toString.
     *
     * @return It returns 'Comment:' with the respective commentary.
     */
    @Override
    public String toString() {
        return "Comment: " + this.comment;
    }

    /**
     * It states if two objects are equals.
     *
     * @param obj The other object to be compared.
     * @return It returns true if the objects are equals or false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Comment other_obj = (Comment) obj;
        return this.comment.equals(other_obj.comment);
    }

    /**
     * The override hashcode method.
     *
     * @return It returns the number of hashcode.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.comment);
        return hash;
    }

}
