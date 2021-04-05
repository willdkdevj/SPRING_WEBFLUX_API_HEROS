package br.com.supernova.apimembers.exception;
import br.com.supernova.apimembers.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends Exception{
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    public MemberNotFoundException(String message) {
        super(String.format("Character with name %s not found in the system", message));
        log.info("There was a problem deleting a member with the name: {}", message);
    }

    public MemberNotFoundException(Long id) {
        super(String.format("Character with ID - %s not found in the system", id));
        log.info("There was a problem deleting a member with the ID - {}", id);
    }
}
