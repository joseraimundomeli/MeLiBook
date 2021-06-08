package br.com.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserClassError extends RuntimeException{
    public UserClassError(String message){
        super(message);
    }
}
