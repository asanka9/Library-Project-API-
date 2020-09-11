package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundingException extends RuntimeException {
	
	public NotFoundingException(String msg) {
		super(msg);
	}

}
