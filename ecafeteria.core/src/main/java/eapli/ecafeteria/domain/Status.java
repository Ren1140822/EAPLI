/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.domain;

import javax.persistence.Embeddable;

/**
 * @author Jorge Santos ajs@isep.ipp.pt
 */

//FIXME: Because Status is an enum, it does not need to be @Embeddable
@Embeddable
public enum Status {
	ACTIVE, APPROVAL_PENDING, INACTIVE;
}
