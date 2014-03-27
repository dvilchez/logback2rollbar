
package com.xuaps.data;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Request {

    @Expose
    private String url;
    @Expose
    private String method;
    @Expose
    private Headers headers;
    @Expose
    private Params params;
    @SerializedName("GET")
    @Expose
    private GET gET;
    @Expose
    private String query_string;
    @SerializedName("POST")
    @Expose
    private POST pOST;
    @Expose
    private String body;
    @Expose
    private String user_ip;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public GET getGET() {
        return gET;
    }

    public void setGET(GET gET) {
        this.gET = gET;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }

    public POST getPOST() {
        return pOST;
    }

    public void setPOST(POST pOST) {
        this.pOST = pOST;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

}
