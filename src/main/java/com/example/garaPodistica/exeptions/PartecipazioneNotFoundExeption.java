package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PartecipazioneNotFoundExeption extends RuntimeException{
    public PartecipazioneNotFoundExeption() {
    }

    public PartecipazioneNotFoundExeption(String message) {
        super(message);
    }

    public PartecipazioneNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public PartecipazioneNotFoundExeption(Throwable cause) {
        super(cause);
    }
}
