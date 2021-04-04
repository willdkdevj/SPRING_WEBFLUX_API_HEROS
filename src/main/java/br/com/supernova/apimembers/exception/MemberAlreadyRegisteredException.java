package br.com.supernova.apimembers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberAlreadyRegisteredException extends Exception{
    public MemberAlreadyRegisteredException(String message) {
        super(String.format("Character with name %s already registered in the system", message));
    }
}
