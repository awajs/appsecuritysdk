package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 21-Jul-2020
 */
public class AppControlHeader implements Parcelable
{

    @SerializedName("alg")
    @Expose
    private String alg;
    @SerializedName("kid")
    @Expose
    private String kid;
    @SerializedName("x5c")
    @Expose
    private List<String> x5c = null;
    @SerializedName("enc")
    @Expose
    private String enc;
    @SerializedName("epk")
    @Expose
    private Epk epk;
    public final static Creator<AppControlHeader> CREATOR = new Creator<AppControlHeader>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppControlHeader createFromParcel(Parcel in) {
            return new AppControlHeader(in);
        }

        public AppControlHeader[] newArray(int size) {
            return (new AppControlHeader[size]);
        }

    }
            ;

    protected AppControlHeader(Parcel in) {
        this.alg = ((String) in.readValue((String.class.getClassLoader())));
        this.kid = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.x5c, (String.class.getClassLoader()));
        this.enc = ((String) in.readValue((String.class.getClassLoader())));
        this.epk = ((Epk) in.readValue((Epk.class.getClassLoader())));
    }

    public AppControlHeader() {
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public List<String> getX5c() {
        return x5c;
    }

    public void setX5c(List<String> x5c) {
        this.x5c = x5c;
    }

    public String getEnc() {
        return enc;
    }

    public void setEnc(String enc) {
        this.enc = enc;
    }

    public Epk getEpk() {
        return epk;
    }

    public void setEpk(Epk epk) {
        this.epk = epk;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(alg);
        dest.writeValue(kid);
        dest.writeList(x5c);
        dest.writeValue(enc);
        dest.writeValue(epk);
    }

    public int describeContents() {
        return 0;
    }

}
