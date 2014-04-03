package com.xuaps.exception;

/**
 * Created by david.vilchez on 2/04/14.
 */
public class CommunicationException extends Throwable {
    public CommunicationException(Exception e) {
        super(e);
    }
}
