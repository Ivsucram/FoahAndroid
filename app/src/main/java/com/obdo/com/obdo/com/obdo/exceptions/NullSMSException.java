package com.obdo.com.obdo.com.obdo.exceptions;

/**
 * Custom Exception for when some method try to use an empty or null SMS code
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 */
public class NullSMSException extends NullPointerException {
    public NullSMSException() {}

    public NullSMSException(String message) {
        super(message);
    }
}
