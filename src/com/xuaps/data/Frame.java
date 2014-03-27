
package com.xuaps.data;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Frame {

    @Expose
    private String filename;
    @Expose
    private Integer lineno;
    @Expose
    private Integer colno;
    @Expose
    private String method;
    @Expose
    private String code;
    @Expose
    private Context context;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getLineno() {
        return lineno;
    }

    public void setLineno(Integer lineno) {
        this.lineno = lineno;
    }

    public Integer getColno() {
        return colno;
    }

    public void setColno(Integer colno) {
        this.colno = colno;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
