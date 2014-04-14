package com.xuaps.ObjectMothers;

import com.xuaps.data.*;

/**
 * Created by david.vilchez on 14/04/14.
 */
public class PayloadObjectMother {
    private final String environment;
    private final String accessToken;

    public PayloadObjectMother(String accessToken, String environment)
    {
        this.accessToken=accessToken;
        this.environment=environment;
    }

    public Payload CreatePayLoad() {
        Payload payload=new Payload();
        payload.setAccess_token(accessToken);
        Data_ data=new Data_();
        data.setEnvironment(environment);
        data.setLanguage("java");
        data.setPlatform("linux");
        payload.setData(data);
        return payload;
    }
}
