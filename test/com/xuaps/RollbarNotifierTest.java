package com.xuaps;

import com.sun.deploy.association.AssociationDesc;
import com.xuaps.data.Trace;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

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
        RollbarNotifier notifier=new RollbarNotifier(environment, access_token);
        RollbarNotifier notifier2=new RollbarNotifier(environment, access_token, platform, language, framework);

        assert(notifier.getEnvironment()).equals(environment);
        assert(notifier.getAccessToken()).equals(access_token);

        assert(notifier2.getEnvironment()).equals(environment);
        assert(notifier2.getAccessToken()).equals(access_token);
        assert(notifier2.getPlatform()).equals(platform);
        assert(notifier2.getLanguage()).equals(language);
        assert(notifier2.getFramework()).equals(framework);
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
    public void notify_exception(){
        RollbarNotifier notifier=new RollbarNotifier(environment, access_token);

        Trace trace=new Trace();

        int result = notifier.NotifyException(trace);

        assertThat("juas").isEqualTo("aaa");
/*
trace
 */
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
