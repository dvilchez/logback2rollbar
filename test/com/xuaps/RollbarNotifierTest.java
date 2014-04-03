package com.xuaps;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.httpclient.BetamaxHttpsSupport;
import co.freeside.betamax.httpclient.BetamaxRoutePlanner;
import com.google.gson.Gson;
import com.xuaps.data.*;
import com.xuaps.exception.AccessDeniedException;
import com.xuaps.exception.UnprocessablePayloadException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Properties;

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
    private Properties props;

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

        props=new Properties();
        try {
            props.load(new FileInputStream("tests.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            //assertThat(e.getMessage()).isEqualTo("access token not found: YOUR_PROJECT_ACCESS_TOKEN");
        }
    }

    @Betamax(tape="notify_execption_unprocessable_payload")
    @Test
    public void notify_exception_unprocessable_payload(){
        Frame frame=new Frame();
        ArrayList<Frame> frames=new ArrayList<Frame>();
        frames.add(frame);
        Trace trace=new Trace();
        trace.setFrames(frames);
        payload.getData().getBody().setTrace(trace);
        payload.setAccess_token(props.getProperty("access_token"));
        try{
            notifier.NotifyException(payload);
            fail("UnprocessablePayloadException expected because payload semantic is wrong");
        }catch(Throwable e){
            //e.printStackTrace();
            assertThat(e).isInstanceOf(UnprocessablePayloadException.class);
            //assertThat(e.getMessage()).isEqualTo("Invalid format. data.body.trace.exception is missing and it is required.");
        }
    }

    @Test
    public void notify_message(){

    }
}
