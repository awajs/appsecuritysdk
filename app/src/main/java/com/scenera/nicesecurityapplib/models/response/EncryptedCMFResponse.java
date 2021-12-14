package com.scenera.nicesecurityapplib.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptedCMFResponse {

    @SerializedName("encryptedPayload")
    @Expose
    private String encryptedPayload;

    @SerializedName("EncryptedPayload")
    @Expose
    private String encryptedPayloadSceneMode;

    public String getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(String encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    public String getEncryptedPayloadSceneMode() {
        return encryptedPayloadSceneMode;
    }

    public void setEncryptedPayloadSceneMode(String encryptedPayloadSceneMode) {
        this.encryptedPayloadSceneMode = encryptedPayloadSceneMode;
    }
}
