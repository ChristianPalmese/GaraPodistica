package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SponsorNotFoundExeption extends RuntimeException{
    public SponsorNotFoundExeption() {
    }

    public SponsorNotFoundExeption(String message) {
        super(message);
    }

    public SponsorNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public SponsorNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
