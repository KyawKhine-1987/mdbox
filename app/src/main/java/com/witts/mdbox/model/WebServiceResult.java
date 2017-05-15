package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;
import com.witts.mdbox.model.enums.MDBCode;

/**
 * Created by thanhtetaung on 18/1/16.
 */
public class WebServiceResult<T>{

    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public static class Meta{
        @SerializedName("responseCode")
        private String code;

        @SerializedName("responseMsg")
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    public boolean hasError(){
        return !meta.getCode().equals("000");
    }
}
