package be.bignited.hugeuserdatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person not found")
public class PersonNotFoundException extends RuntimeException {
}
