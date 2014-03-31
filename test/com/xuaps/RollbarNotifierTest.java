package com.xuaps;

import com.xuaps.data.Trace;
import org.junit.Before;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by david.vilchez on 26/03/14.
 */
public class RollbarNotifierTest {

    private String environment;
    private String access_token;
    private String platform;
    private String language;
    private String framework;
    private HttpClient httpClient;

    @Before
    public void setUp(){
        environment="production";
        access_token="YOUR_PROJECT_ACCESS_TOKEN";
        platform="linux";
        language="python";
        framework="pyramid";
    }

    @Test
    public void initialize_rollbar_notifier(){
        RollbarNotifier notifier=new RollbarNotifier(httpClient, environment, access_token);
        RollbarNotifier notifier2=new RollbarNotifier(httpClient,environment, access_token, platform, language, framework);

        assertThat(notifier.getEnvironment()).isEqualTo(environment);
        assertThat(notifier.getAccessToken()).isEqualTo(access_token);

        assertThat(notifier2.getEnvironment()).isEqualTo(environment);
        assertThat(notifier2.getAccessToken()).isEqualTo(access_token);
        assertThat(notifier2.getPlatform()).isEqualTo(platform);
        assertThat(notifier2.getLanguage()).isEqualTo(language);
        assertThat(notifier2.getFramework()).isEqualTo(framework);
    }
/*
"context": "project#index",
request
person
server
client
custom
fingerprint
tittle
 */
    @Test
    public void notify_exception_success(){
        RollbarNotifier notifier=new RollbarNotifier(httpClient, environment, access_token);

        Trace trace=new Trace();

        RollbarResult result = notifier.NotifyException(trace);

        assertThat(result).isEqualTo(RollbarResult.Success);
        //se hizo una llamada al servidor de rollabar con el json correcto
    }

    @Test
    public void notify_message(){
/*
message
 */
    }

    /*
    // Optional: timestamp
    // When this occurred, as a unix timestamp.
    "timestamp": 1369188822,
    notfier
     */
}
