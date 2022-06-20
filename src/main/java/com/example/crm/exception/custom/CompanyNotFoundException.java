package com.example.crm.exception.custom;

import com.example.crm.exception.base.ValidationException;

import java.text.MessageFormat;

/**
 * @author Ferruhcan Kuruoğlu
 */
public class CompanyNotFoundException extends ValidationException {
    private static final String MESSAGE = "A company associated with given {0} was not found. {0} : {1}";
    public CompanyNotFoundException(int id) {
        super(MessageFormat.format(MESSAGE, "ID", id));
    }
    public CompanyNotFoundException(String name) {
        super(MessageFormat.format(MESSAGE, "NAME", name));
    }
}
