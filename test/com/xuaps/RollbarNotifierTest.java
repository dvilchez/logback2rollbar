package com.xuaps;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.httpclient.BetamaxRoutePlanner;
import com.sun.javaws.exceptions.InvalidArgumentException;
import com.xuaps.data.Body;
import com.xuaps.data.Data_;
import com.xuaps.data.Payload;
import com.xuaps.data.Trace;
import org.apache.http.impl.client.DefaultHttpClient;
import org.fest.assertions.ThrowableAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;


/**
 * Created by david.vilchez on 26/03/14.
 */
public class RollbarNotifierTest {

    private RollbarNotifier notifier;
    private Payload payload;

//    @Rule
//    public Recorder recorder = new Recorder();

    @Before
    public void setUp(){
        DefaultHttpClient httpClient=new DefaultHttpClient();
        //BetamaxRoutePlanner.configure(httpClient);
        notifier=new RollbarNotifier(httpClient);

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
    public void notify_exception_check_exist_trace() throws java.io.IOException{

        try {
            notifier.NotifyException(payload);
            fail("IllegalArgumentException expected because Data hasn´t Trace");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
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
