/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

/**
 * A value object is a Domain-Driven Design pattern for domain concepts which do
 * not have a thread of continuity neither need to be tracked by their identity
 * but for the value of its attributes. these are immutable objects which can be
 * freely shared and discarded and replaced by another instance. equality is
 * done thru comparison of the attributes' values.
 *
 * Typical examples are:
 * <p>
 * - Address (in most scenarios)
 * <p>
 * - Color<p>
 * - CustomerNumber<p>
 * - Money
 *
 * @author Paulo Gandra Sousa
 */
public interface ValueObject {

    @Override
    public boolean equals(Object other);

    @Override
    public int hashCode();
}
