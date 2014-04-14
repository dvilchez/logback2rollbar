package com.xuaps;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by david.vilchez on 14/04/14.
 */
public class RollbarAppendderTest {
    private Properties props;

    @Before
    public void setUp(){
        props=new Properties();
        try {
            props.load(new FileInputStream("tests.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exception_occurs(){
        RollbarAppender app=new RollbarAppender();
        app.setAccessToken(props.getProperty("access_token"));
        app.setEnvironment("test");

        StackTraceElement[] context=new StackTraceElement[1];
        context[0]=new StackTraceElement("tachan","juas", "C:\\", 10);
        LoggingEvent event= new LoggingEvent();
        event.setCallerData(context);
        event.setLevel(Level.ERROR);
        event.setTimeStamp(System.currentTimeMillis() / 1000L);
        event.setMessage("ERRORRRRRRRRR!!!!");

        app.start();
        app.append(event);

        //assert. go to rollbar panel to check it :)
    }
}
