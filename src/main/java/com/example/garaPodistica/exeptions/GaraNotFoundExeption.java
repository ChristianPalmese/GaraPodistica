package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GaraNotFoundExeption extends RuntimeException{
    public GaraNotFoundExeption() {
    }

    public GaraNotFoundExeption(String message) {
        super(message);
    }

    public GaraNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public GaraNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
