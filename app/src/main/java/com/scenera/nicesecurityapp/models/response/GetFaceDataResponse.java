package com.scenera.nicesecurityapp.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapp.models.data.PersonFace;

import java.util.List;

public class GetFaceDataResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("faces")
    @Expose
    private List<PersonFace> faces;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PersonFace> getFaces() {
        return faces;
    }

    public void setFaces(List<PersonFace> faces) {
        this.faces = faces;
    }
}
