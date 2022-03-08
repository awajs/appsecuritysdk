package com.scenera.nicesecurityapplib.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EncryptedCMFResponse {

    @SerializedName("EncryptedPayload")
    @Expose
    private String EncryptedPayload;

    @SerializedName("encryptedPayload")
    @Expose
    private String encryptedPayload;

    public String getEncryptedPayload() {
        return EncryptedPayload;
    }

    public void setEncryptedPayload(String encryptedPayload) {
        this.EncryptedPayload = encryptedPayload;
    }

    public String getencryptedPayload() {
        return encryptedPayload;
    }

    public void setencryptedPayload(String encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }
}
