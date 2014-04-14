package com.xuaps;


import com.google.gson.Gson;
import com.xuaps.data.Data;
import com.xuaps.data.Payload;
import com.xuaps.exception.AccessDeniedException;
import com.xuaps.exception.CommunicationException;
import com.xuaps.exception.RollbarException;
import com.xuaps.exception.UnprocessablePayloadException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import static com.xuaps.StringUtils.InputStreamToString;

/**
 * Created by david.vilchez on 27/03/14.
 */
public class RollbarNotifier {
    private final HttpClient httpClient;
    private final Gson gson;

    public RollbarNotifier(HttpClient httpClient, Gson gson){
        this.httpClient=httpClient;
        this.gson=gson;
    }

    public void NotifyException(Payload payload) throws Throwable {
        //extract this logic to payload object, notify only accepts valid exceptions
        ValidateExceptionPayload(payload);
        HttpResponse response=Send(BuildJson(payload));

        if(response.getStatusLine().getStatusCode()!=200){
            String content=
                    gson.fromJson(InputStreamToString(response.getEntity().getContent()),com.xuaps.data.Error.class).getMessage();
                throw new RollbarException(content);
        }
    }

    private String BuildJson(Payload payload) {
        Data data=new Data();
        data.setPayload(payload);
        return gson.toJson(data);
    }

    private void ValidateExceptionPayload(Payload payload) {
        if(payload.getData().getBody().getTrace()==null){
            throw new IllegalArgumentException("Trace data are required.");
        }else if(payload.getData().getBody().getMessage()!=null){
            throw new IllegalArgumentException("Data can´t contain Message");
        }
    }

    private HttpResponse Send(String data) throws Throwable {
        HttpPost request = new HttpPost("https://api.rollbar.com/api/1/item/");
        request.addHeader("content-type", "application/json");
        try {
            request.setEntity(new StringEntity(data));
            return httpClient.execute(request);
        } catch (Exception e) {
            throw new CommunicationException(e);
        }
    }
}
