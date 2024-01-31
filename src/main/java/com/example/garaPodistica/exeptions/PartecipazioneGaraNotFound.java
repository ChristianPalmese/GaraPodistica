package com.example.garaPodistica.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PartecipazioneGaraNotFound extends RuntimeException{
    public PartecipazioneGaraNotFound() {
    }

    public PartecipazioneGaraNotFound(String message) {
        super(message);
    }

    public PartecipazioneGaraNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PartecipazioneGaraNotFound(Throwable cause) {
        super(cause);
    }
}
