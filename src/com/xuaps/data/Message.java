
package com.xuaps.data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Message {

    @Expose
    private String body;
    @Expose
    private String user_id;
    @Expose
    private List<Integer> lucky_numbers = new ArrayList<Integer>();

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<Integer> getLucky_numbers() {
        return lucky_numbers;
    }

    public void setLucky_numbers(List<Integer> lucky_numbers) {
        this.lucky_numbers = lucky_numbers;
    }

}
