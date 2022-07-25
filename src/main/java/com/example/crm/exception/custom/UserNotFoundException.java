package com.example.crm.exception.custom;

import com.example.crm.exception.base.ValidationException;

import java.text.MessageFormat;

public class UserNotFoundException extends ValidationException {
    private static final String MESSAGE = "A user associated with given {0} was not found. {0} = {1}";
    public UserNotFoundException(Integer id) {super(MessageFormat.format(MESSAGE, "id", id));}
    public UserNotFoundException(String name) {super(MessageFormat.format(MESSAGE, "name", name));}
}
