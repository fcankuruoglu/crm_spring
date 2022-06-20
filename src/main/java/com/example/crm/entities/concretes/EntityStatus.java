package com.example.crm.entities.concretes;

import com.example.crm.exception.custom.EntityStatusNotFoundException;

/**
 * @author Ferruhcan Kuruoğlu

 **/
public enum EntityStatus {
    /**
     * Object is active.
     */
    ACTIVE(0),
    /**
     * Object is deleted.
     */
    DELETED(1),
    /**
     * Object is disable.
     */
    DISABLE(2);
    private final Integer value;
    EntityStatus(Integer i) {
        this.value = i;
    }
    public int getValue() {
        return this.value;
    }

    public static EntityStatus getStatus(Integer value) {
        if (value == 0) {
            return EntityStatus.ACTIVE;
        } else if (value == 1) {
            return EntityStatus.DELETED;
        } else if (value == 2) {
            return EntityStatus.DISABLE;
        } else {
            throw new EntityStatusNotFoundException(value);
        }
    }
}
