package com.example.crm.exception.custom;

import com.example.crm.exception.base.ValidationException;

import java.text.MessageFormat;

/**
 * @author Ferruhcan Kuruoğlu
 */
public class EntityStatusNotFoundException extends ValidationException {
    private static final String MESSAGE = "A status with given {0} was not found. {0} : {1}";
    public EntityStatusNotFoundException(Integer value) {
        super(MessageFormat.format(MESSAGE, "value", value));
    }
}
