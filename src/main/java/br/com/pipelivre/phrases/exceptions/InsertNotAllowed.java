package br.com.pipelivre.phrases.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Method not allowed")
public class InsertNotAllowed extends RuntimeException {
}
