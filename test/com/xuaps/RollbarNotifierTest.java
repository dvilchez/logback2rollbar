package com.xuaps;

import com.xuaps.data.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;

import java.lang.Exception;

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
    public void notify_exception_checks_trace_is_present(){

        try {
            notifier.NotifyException(payload);
            fail("IllegalArgumentException expected because Data hasn´t Trace");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void notify_exception_checks_message_isnt_present(){
        payload.getData().getBody().setTrace(new Trace());
        payload.getData().getBody().setMessage(new Message());
        try {
            notifier.NotifyException(payload);
            fail("IllegalArgumentException expected because Data can´t contain Trace and Message");
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
