package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload_1 {

    @SerializedName("Body")
    @Expose
    private Body1 body;

    public Body1 getBody() {
        return body;
    }

    public void setBody(Body1 body) {
        this.body = body;
    }
}
