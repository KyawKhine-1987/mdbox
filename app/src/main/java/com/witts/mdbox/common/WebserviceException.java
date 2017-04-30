package com.witts.mdbox.common;

import java.io.IOException;

/**
 * Created by winhlaingtun on 2/17/17.
 */

public class WebserviceException extends IOException {
    private String errorMessage;
    private String error;
    private int code;

    public WebserviceException(){
        super();
    }

    public WebserviceException(String desc){
        super(desc);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
