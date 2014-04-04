package com.xuaps.builders;

import com.xuaps.data.*;

/**
 * Created by david.vilchez on 3/04/14.
 */
public class PayloadBuilder {
    private final Payload payload;

    public PayloadBuilder(){
        Body body=new Body();
        Data_ data=new Data_();
        data.setBody(body);
        data.setEnvironment("production");
        data.setPlatform("linux");
        data.setLanguage("python");
        data.setFramework("pyramid");
        payload=new Payload();
        payload.setAccess_token("YOUR_PROJECT_ACCESS_TOKEN");
        payload.setData(data);
    }

    public static PayloadBuilder DefaultPayload(){
        return new PayloadBuilder();
    }

    public Payload Build(){
        return payload;
    }

    public PayloadBuilder WithTrace() {
        payload.getData().getBody().setTrace(new Trace());
        return this;
    }

    public PayloadBuilder WithMessage() {
        payload.getData().getBody().setMessage(new Message());
        return this;
    }

    public PayloadBuilder WithAccessToken(String access_token) {
        payload.setAccess_token(access_token);
        return this;
    }
}
