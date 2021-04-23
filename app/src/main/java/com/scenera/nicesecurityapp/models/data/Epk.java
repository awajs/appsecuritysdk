package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
public class Epk implements Parcelable
{

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
    public final static Creator<Epk> CREATOR = new Creator<Epk>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Epk createFromParcel(Parcel in) {
            return new Epk(in);
        }

        public Epk[] newArray(int size) {
            return (new Epk[size]);
        }

    }
            ;

    protected Epk(Parcel in) {
        this.kty = ((String) in.readValue((String.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
        this.crv = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Epk() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kty);
        dest.writeValue(x);
        dest.writeValue(y);
        dest.writeValue(crv);
    }

    public int describeContents() {
        return 0;
    }

}
