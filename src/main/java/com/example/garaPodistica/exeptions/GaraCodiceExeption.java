package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GaraCodiceExeption extends RuntimeException{
    public GaraCodiceExeption() {
        super();
    }

    public GaraCodiceExeption(String message) {
        super(message);
    }

    public GaraCodiceExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public GaraCodiceExeption(Throwable cause) {
        super(cause);
    }
}
