package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class DetectedImageCMF implements Parcelable
{

    @SerializedName("Encryption")
    @Expose
    private Encryption encryption;
    @SerializedName("ImageURI")
    @Expose
    private String imageURI;
    public final static Creator<DetectedImageCMF> CREATOR = new Creator<DetectedImageCMF>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetectedImageCMF createFromParcel(Parcel in) {
            return new DetectedImageCMF(in);
        }

        public DetectedImageCMF[] newArray(int size) {
            return (new DetectedImageCMF[size]);
        }

    }
            ;

    protected DetectedImageCMF(Parcel in) {
        this.encryption = ((Encryption) in.readValue((Encryption.class.getClassLoader())));
        this.imageURI = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public DetectedImageCMF() {
    }

    /**
     *
     * @param imageURI
     * @param encryption
     */
    public DetectedImageCMF(Encryption encryption, String imageURI) {
        super();
        this.encryption = encryption;
        this.imageURI = imageURI;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(encryption);
        dest.writeValue(imageURI);
    }

    public int describeContents() {
        return 0;
    }

}