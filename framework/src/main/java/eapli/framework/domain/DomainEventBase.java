/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.domain;

import eapli.framework.domain.DomainEvent;
import eapli.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Paulo Gandra Sousa
 */
public abstract class DomainEventBase implements DomainEvent {

    private final Calendar occuredAt;
    private final Calendar registeredAt;

    public DomainEventBase(Calendar occuredAt) {
        this.occuredAt = occuredAt;
        this.registeredAt = DateTime.now();
    }

    @Override
    public Calendar occurredAt() {
        return occuredAt;
    }

    @Override
    public Calendar registeredAt() {
        return registeredAt;
    }
}
