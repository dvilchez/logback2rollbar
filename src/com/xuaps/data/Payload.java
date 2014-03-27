
package com.xuaps.data;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Payload {

    @Expose
    private String access_token;
    @Expose
    private Data_ data;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

}
