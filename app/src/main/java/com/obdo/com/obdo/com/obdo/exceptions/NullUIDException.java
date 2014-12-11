package com.obdo.com.obdo.com.obdo.exceptions;

/**
 * Custom Exception for when some method try to use an empty or null UID
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 */
public class NullUIDException extends NullPointerException {
    public NullUIDException() {}

    public NullUIDException(String message) {
        super(message);
    }
}
