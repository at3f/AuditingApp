package com.fawry.auditing_v1.exceptions;

import javax.persistence.EntityNotFoundException;

public class BeNotFoundException extends EntityNotFoundException {
    public BeNotFoundException(String message) {
        super(message);
    }
}
