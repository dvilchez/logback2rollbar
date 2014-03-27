
package com.xuaps.data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Context {

    @Expose
    private List<String> pre = new ArrayList<String>();
    @Expose
    private List<Object> post = new ArrayList<Object>();

    public List<String> getPre() {
        return pre;
    }

    public void setPre(List<String> pre) {
        this.pre = pre;
    }

    public List<Object> getPost() {
        return post;
    }

    public void setPost(List<Object> post) {
        this.post = post;
    }

}
