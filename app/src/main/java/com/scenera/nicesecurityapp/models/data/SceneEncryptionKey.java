package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SceneEncryptionKey implements Parcelable {
    @SerializedName("alg")
    @Expose
    private String alg;
    @SerializedName("k")
    @Expose
    private String k;
    @SerializedName("iv")
    @Expose
    private String iv;
    @SerializedName("kid")
    @Expose
    private String kid;
    public final static Creator<SceneEncryptionKey> CREATOR = new Creator<SceneEncryptionKey>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneEncryptionKey createFromParcel(Parcel in) {
            return new SceneEncryptionKey(in);
        }

        public SceneEncryptionKey[] newArray(int size) {
            return (new SceneEncryptionKey[size]);
        }

    }
            ;

    protected SceneEncryptionKey(Parcel in) {
        this.alg = ((String) in.readValue((String.class.getClassLoader())));
        this.k = ((String) in.readValue((String.class.getClassLoader())));
        this.iv = ((String) in.readValue((String.class.getClassLoader())));
        this.kid = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SceneEncryptionKey() {
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(alg);
        dest.writeValue(k);
        dest.writeValue(iv);
        dest.writeValue(kid);
    }

    public int describeContents() {
        return 0;
    }
}
