package com.smartpan.smartpaninterviewtask.modules.login.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String Message;
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Image")
    @Expose
    private String image;

    public LoginResponse() {

    }

    public LoginResponse(String errorMsg) {
        this.success = "failed";
        this.setMessage(errorMsg);
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
