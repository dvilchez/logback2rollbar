package com.xuaps;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.httpclient.BetamaxHttpsSupport;
import co.freeside.betamax.httpclient.BetamaxRoutePlanner;
import com.google.gson.Gson;
import com.xuaps.data.*;
import com.xuaps.exception.AccessDeniedException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;


/**
 * Created by david.vilchez on 26/03/14.
 */
public class RollbarNotifierTest {

    private RollbarNotifier notifier;
    private Payload payload;

    @Rule
    public Recorder recorder = new Recorder();

    @Before
    public void setUp(){
        DefaultHttpClient httpClient=new DefaultHttpClient();
        Gson gson=new Gson();
        BetamaxRoutePlanner.configure(httpClient);
        BetamaxHttpsSupport.configure(httpClient);
        notifier=new RollbarNotifier(httpClient, gson);

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
        } catch (Throwable e) {
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
        } catch (Throwable e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Betamax(tape="notify_execption_access_denied")
    @Test
    public void notify_exception_access_denied(){
        Frame frame=new Frame();
        frame.setFilename("filename.java");
        ArrayList<Frame> frames=new ArrayList<Frame>();
        frames.add(frame);
        Trace trace=new Trace();
        trace.setFrames(frames);
        payload.getData().getBody().setTrace(trace);
        try{
            notifier.NotifyException(payload);
            fail("AccessDeniedException expected because access token is wrong");
        }catch(Throwable e){
            assertThat(e).isInstanceOf(AccessDeniedException.class);
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
