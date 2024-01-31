package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GaraCodiceNotFound extends RuntimeException {
    public GaraCodiceNotFound() {
        super();
    }

    public GaraCodiceNotFound(String message) {
        super(message);
    }

    public GaraCodiceNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public GaraCodiceNotFound(Throwable cause) {
        super(cause);
    }
}
