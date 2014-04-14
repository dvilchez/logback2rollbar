package com.xuaps.exception;

/**
 * Created by david.vilchez on 14/04/14.
 */
public class RollbarException extends Throwable {
    public RollbarException(String content) {
        super(content);
    }
}
