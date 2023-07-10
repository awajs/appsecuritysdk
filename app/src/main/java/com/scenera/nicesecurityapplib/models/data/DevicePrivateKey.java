package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DevicePrivateKey implements Parcelable {

    @SerializedName("kty")
    @Expose
    private String kty;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("crv")
    @Expose
    private String crv;
    @SerializedName("d")
    @Expose
    private String d;

    protected DevicePrivateKey(Parcel in) {
        kty = in.readString();
        x = in.readString();
        y = in.readString();
        crv = in.readString();
        d = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kty);
        dest.writeString(x);
        dest.writeString(y);
        dest.writeString(crv);
        dest.writeString(d);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DevicePrivateKey> CREATOR = new Creator<DevicePrivateKey>() {
        @Override
        public DevicePrivateKey createFromParcel(Parcel in) {
            return new DevicePrivateKey(in);
        }

        @Override
        public DevicePrivateKey[] newArray(int size) {
            return new DevicePrivateKey[size];
        }
    };

    public String getKty() {
        return kty;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getCrv() {
        return crv;
    }

    public void setCrv(String crv) {
        this.crv = crv;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
