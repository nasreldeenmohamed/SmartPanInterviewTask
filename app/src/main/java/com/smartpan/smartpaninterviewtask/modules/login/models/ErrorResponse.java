package com.smartpan.smartpaninterviewtask.modules.login.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String Message;

    public ErrorResponse(String success, String message) {
        this.success = success;
        Message = message;
    }

    public ErrorResponse() {

    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return Message;
    }
}
