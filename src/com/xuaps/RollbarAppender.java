package com.xuaps;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.google.gson.Gson;
import com.xuaps.ObjectMothers.BodyObjectMother;
import com.xuaps.ObjectMothers.PayloadObjectMother;
import com.xuaps.data.Body;
import com.xuaps.data.Frame;
import com.xuaps.data.Payload;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by david.vilchez on 26/03/14.
 */
public class RollbarAppender extends AppenderBase<ILoggingEvent> {
    private RollbarNotifier notifier;
    private String accessToken;
    private String environment;
    private Payload payload;

    @Override
    public void start() {
        notifier=new RollbarNotifier(new DefaultHttpClient(), new Gson());
        payload=new PayloadObjectMother(getAccessToken(), getEnvironment()).CreatePayLoad();
        super.start();
    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        payload.getData().setLevel(iLoggingEvent.getLevel().toString());
        payload.getData().setTimestamp((int) iLoggingEvent.getTimeStamp());
        Body body = new BodyObjectMother().CreateBody();
        payload.getData().setBody(body);

        body.getTrace().getException().setMessage(iLoggingEvent.getMessage());
        for(StackTraceElement element: iLoggingEvent.getCallerData()){
            Frame frame=new Frame();
            frame.setFilename(element.getFileName());
            frame.setMethod(element.getMethodName());
            frame.setLineno(element.getLineNumber());
            body.getTrace().getException().setClass_(element.getClassName());

            body.getTrace().getFrames().add(frame);
        }

        try {
            notifier.NotifyException(payload);
        } catch (Throwable throwable) {
            addError(throwable.toString());
        }
    }

    public String getAccessToken(){
        return accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken=accessToken;
    }

    public String getEnvironment(){
        return environment;
    }

    public void setEnvironment(String environment){
        this.environment=environment;
    }
}
