package com.xuaps;


import com.xuaps.data.Payload;
import org.apache.http.client.HttpClient;

/**
 * Created by david.vilchez on 27/03/14.
 */
public class RollbarNotifier {
    private final HttpClient httpClient;

    public RollbarNotifier(HttpClient httpClient){
        this.httpClient=httpClient;
    }

    public void NotifyException(Payload payload) throws IllegalArgumentException {
        if(payload.getData().getBody().getTrace()==null){
            throw new IllegalArgumentException("Trace data are required.");
        }
    }
}
