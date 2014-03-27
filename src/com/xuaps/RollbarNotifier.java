package com.xuaps;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuaps.data.Data;
import com.xuaps.data.Data_;
import com.xuaps.data.Payload;
import com.xuaps.data.Trace;

/**
 * Created by david.vilchez on 27/03/14.
 */
public class RollbarNotifier {
    private final Payload payload;
    private final Data_ data;
    private final Gson gson = new GsonBuilder().create();
    private String pay;

    public RollbarNotifier(String environment, String access_token) {
        data=new Data_();
        data.setEnvironment(environment);

        payload=new Payload();
        payload.setAccess_token(access_token);
        payload.setData(data);
    }

    public RollbarNotifier(String environment, String access_token, String platform, String language, String framework) {
        this(environment, access_token);

        data.setPlatform(platform);
        data.setLanguage(language);
        data.setFramework(framework);
    }

    public String getEnvironment() {
        return data.getEnvironment();
    }

    public String getAccessToken() {
        return payload.getAccess_token();
    }

    public String getPlatform() {
        return data.getPlatform();
    }

    public String getLanguage() {
        return data.getLanguage();
    }

    public String getFramework() {
        return data.getFramework();
    }

    public int NotifyException(Trace trace) {
        return 0;
    }
}
