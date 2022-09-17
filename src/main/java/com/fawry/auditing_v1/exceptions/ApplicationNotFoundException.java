package com.fawry.auditing_v1.exceptions;

import javax.persistence.EntityNotFoundException;

public class ApplicationNotFoundException extends EntityNotFoundException {
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
