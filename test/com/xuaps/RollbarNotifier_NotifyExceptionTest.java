package com.xuaps;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;
import co.freeside.betamax.httpclient.BetamaxHttpsSupport;
import co.freeside.betamax.httpclient.BetamaxRoutePlanner;
import com.google.gson.Gson;
import com.xuaps.exception.AccessDeniedException;
import com.xuaps.exception.RollbarException;
import com.xuaps.exception.UnprocessablePayloadException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.xuaps.builders.PayloadBuilder.DefaultPayload;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;


/**
 * Created by david.vilchez on 26/03/14.
 */
public class RollbarNotifier_NotifyExceptionTest {

    private RollbarNotifier notifier;

    @Rule
    public Recorder recorder = new Recorder();
    private Properties props;

    @Before
    public void setUp(){
        DefaultHttpClient httpClient= new DefaultHttpClient();
        BetamaxRoutePlanner.configure(httpClient);
        BetamaxHttpsSupport.configure(httpClient);
        notifier=new RollbarNotifier(httpClient, new Gson());

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
            notifier.NotifyException(DefaultPayload().Build());
            fail("IllegalArgumentException expected because Data hasn´t Trace");
        } catch (Throwable e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void notify_exception_checks_message_isnt_present(){
        try {
            notifier.NotifyException(DefaultPayload().WithTrace().WithMessage().Build());
            fail("IllegalArgumentException expected because Data can´t contain Trace and Message");
        } catch (Throwable e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Betamax(tape="notify_exception_access_denied")
    @Test
    public void notify_exception_access_denied() throws IOException {
        try{
            notifier.NotifyException(DefaultPayload().WithTrace().Build());
            fail("RollbarException expected because access token is wrong");
        }catch(Throwable e){
            assertThat(e).isInstanceOf(RollbarException.class);
            assertThat(e.getMessage()).isEqualTo("access token not found: YOUR_PROJECT_ACCESS_TOKEN");
        }
    }

    @Betamax(tape="notify_exception_unprocessable_payload")
    @Test
    public void notify_exception_unprocessable_payload(){
        try{
            notifier.NotifyException(DefaultPayload().WithTrace().WithAccessToken(props.getProperty("access_token")).Build());
            fail("RollbarException expected because payload semantic is wrong");
        }catch(Throwable e){
            assertThat(e).isInstanceOf(RollbarException.class);
            assertThat(e.getMessage()).isEqualTo("Invalid format. data.body.trace.exception is missing and it is required.");
        }
    }
}
