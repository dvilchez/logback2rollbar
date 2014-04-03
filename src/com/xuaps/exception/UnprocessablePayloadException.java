package com.xuaps.exception;

/**
 * Created by david.vilchez on 3/04/14.
 */
public class UnprocessablePayloadException extends Throwable {
    public UnprocessablePayloadException(Error error) {
        super(error.getMessage());
    }
}
