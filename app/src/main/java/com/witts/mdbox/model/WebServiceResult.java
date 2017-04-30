package com.witts.mdbox.model;

import com.google.gson.annotations.SerializedName;
import com.witts.mdbox.model.enums.MDBCode;

/**
 * Created by thanhtetaung on 18/1/16.
 */
public class WebServiceResult<T>{

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("returnCode")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("error_description")
    private String errorDescription;

    public String getAccessToken(){
        return accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public String getRefreshToken(){
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

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

    public String getErrorDescription(){
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription){
        this.errorDescription = errorDescription;
    }

    public boolean hasError(){
        return getMDBCode() != MDBCode.REQUEST_SUCCESS;
    }

    public MDBCode getMDBCode(){
        return MDBCode.fromCode(getCode());
    }
}
