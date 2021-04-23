package com.scenera.nicesecurityapp.models.data;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body implements Parcelable
{

    @SerializedName("encryptedPayload")
    @Expose
    private String encryptedPayload;
    public final static Creator<Body> CREATOR = new Creator<Body>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Body createFromParcel(Parcel in) {
            return new Body(in);
        }

        public Body[] newArray(int size) {
            return (new Body[size]);
        }

    }
            ;

    protected Body(Parcel in) {
        this.encryptedPayload = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Body() {
    }

    public String getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(String encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(encryptedPayload);
    }

    public int describeContents() {
        return 0;
    }

}