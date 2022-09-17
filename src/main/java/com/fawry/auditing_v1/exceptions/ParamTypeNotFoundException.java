package com.fawry.auditing_v1.exceptions;

import javax.persistence.EntityNotFoundException;

public class ParamTypeNotFoundException extends EntityNotFoundException {
    public ParamTypeNotFoundException(String message) {
        super(message);
    }
}
