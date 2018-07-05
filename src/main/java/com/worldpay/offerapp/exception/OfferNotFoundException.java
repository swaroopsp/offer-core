package com.worldpay.offerapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8251851744700257233L;
	public OfferNotFoundException() {
        super();
    }
    public OfferNotFoundException(String s) {
        super(s);
    }
    public OfferNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public OfferNotFoundException(Throwable throwable) {
        super(throwable);
    }
  }