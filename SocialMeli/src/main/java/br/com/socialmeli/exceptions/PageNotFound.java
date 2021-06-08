package br.com.socialmeli.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PageNotFound extends RuntimeException{
    public PageNotFound(String message) {
        super(message);
    }
}
