package com.obdo.exceptions;

/**
 * Custom Exception for when some method try to use an empty or null phone number
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 */
public class NullPhoneNumberException extends NullPointerException {
    public NullPhoneNumberException(){}

    public NullPhoneNumberException(String message) {
        super(message);
    }
}
