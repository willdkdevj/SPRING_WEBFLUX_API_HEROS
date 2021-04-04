package br.com.supernova.apimembers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends Exception{
    public MemberNotFoundException(String message) {
        super(String.format("Character with name %s not found in the system", message));
    }

    public MemberNotFoundException(Long id) {
        super(String.format("Character with ID - %s not found in the system", id));
    }
}
