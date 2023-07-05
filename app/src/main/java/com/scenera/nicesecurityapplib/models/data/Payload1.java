package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload1 {

    @SerializedName("Payload")
    @Expose
    private Payload_1 payload;

    public Payload_1 getPayload() {
        return payload;
    }

    public void setPayload(Payload_1 payload) {
        this.payload = payload;
    }


}
