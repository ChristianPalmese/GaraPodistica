package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AtletaNotFoundExeption extends RuntimeException{
    public AtletaNotFoundExeption() {
        super();
    }

    public AtletaNotFoundExeption(String message) {
        super(message);
    }

    public AtletaNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public AtletaNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
