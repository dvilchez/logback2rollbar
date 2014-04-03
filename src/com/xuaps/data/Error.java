package com.xuaps.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by david.vilchez on 3/04/14.
 */
public class Error {
    @SerializedName("err")
    @Expose
    private String err;
    @SerializedName("message")
    @Expose
    private String message;

    public String getErr() {
        return err;
    }

    public String getMessage() {
        return message;
    }
}
