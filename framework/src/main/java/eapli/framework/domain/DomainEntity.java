/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain;

/**
 * An entity is a Domain-Driven Design pattern for concepts in the domain which
 * have a thread of continuity which needs to be tracked. these concepts matter
 * by their identity and we need to track them continuously. instance equality
 * must be done thru the identity of the objects and we cannot loose track or
 * allow duplication of an entity
 *
 * Typical examples are:
 * <ol>
 * <li>Product
 * <li>Person
 * <li>Account
 * </ol>
 *
 * @author Paulo Gandra Sousa
 * @param <ID>
 *            the type of the primary <b>business</b> id of the entity
 */
public interface DomainEntity<ID> extends Identifiable<ID> {

	/**
	 * returns the primary <b>business</b> id of the entity
	 *
	 * @return the primary <b>business</b> id of the entity
	 */
	@Override
	ID id();

	/**
	 * Entities are compared by identity only.
	 *
	 * @param other
	 * @return
	 */
	@Override
	public boolean equals(Object other);

	@Override
	public int hashCode();
}
