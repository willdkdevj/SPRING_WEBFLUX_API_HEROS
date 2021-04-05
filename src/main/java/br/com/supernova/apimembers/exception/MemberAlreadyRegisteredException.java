package br.com.supernova.apimembers.exception;
import br.com.supernova.apimembers.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberAlreadyRegisteredException extends Exception{
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    public MemberAlreadyRegisteredException(String message) {
        super(String.format("Character with name %s already registered in the system", message));
        log.info("There was a problem registering a member, as a member with this name already exists", message);
    }
}
