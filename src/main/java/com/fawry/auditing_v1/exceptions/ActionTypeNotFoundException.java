package com.fawry.auditing_v1.exceptions;

import javax.persistence.EntityNotFoundException;

public class ActionTypeNotFoundException extends EntityNotFoundException {
    public ActionTypeNotFoundException(String message) {
        super(message);
    }
}
