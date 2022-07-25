package com.example.crm.exception.custom;

import com.example.crm.exception.base.ValidationException;

import java.text.MessageFormat;

public class AlreadyExistsException extends ValidationException {
    private static final String MESSAGE = "The data with given info is already exists in db. {0} : {1}";

    public AlreadyExistsException(Integer id) {
        super(MessageFormat.format(MESSAGE, "id", id));
    }


}
