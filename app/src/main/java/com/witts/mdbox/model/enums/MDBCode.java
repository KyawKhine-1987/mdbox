package com.witts.mdbox.model.enums;

/**
 * Created by winhlaingtun on 4/30/17.
 */

public enum MDBCode {
    //TODO change webservice error handling
    SYSTEM_ERROR("999", "System Error"),
    REQUEST_SUCCESS("200", "Success"),
    INVALID_ACCESS_TOKEN("011", "Invalid Access Token"),
    NO_INTERNET_CONNECTION_AVAILABLE("911","No Internet Connection is available!");

    private String code;
    private String message;

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    MDBCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MDBCode fromCode(String code) {
        for (MDBCode cac: MDBCode.values()) {
            if (cac.code.equals(code)) return cac;
        }
        return MDBCode.SYSTEM_ERROR;
    }
}
