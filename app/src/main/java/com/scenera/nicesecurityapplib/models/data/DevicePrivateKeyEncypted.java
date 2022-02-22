package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DevicePrivateKeyEncypted {

    @SerializedName("EncryptedKey")
    @Expose
    private String encryptedKey;
    @SerializedName("EncryptionKeyID")
    @Expose
    private String encryptionKeyID;

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getEncryptionKeyID() {
        return encryptionKeyID;
    }

    public void setEncryptionKeyID(String encryptionKeyID) {
        this.encryptionKeyID = encryptionKeyID;
    }

}
