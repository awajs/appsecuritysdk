package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class DetectedImage  implements Parcelable
{

    @SerializedName("Encryption")
    @Expose
    private Encryption encryption;
    @SerializedName("StreamImageURI")
    @Expose
    private String streamImageURI;
    @SerializedName("ImageData")
    @Expose
    private String imageData;
    public final static Creator<DetectedImage> CREATOR = new Creator<DetectedImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetectedImage createFromParcel(Parcel in) {
            return new DetectedImage(in);
        }

        public DetectedImage[] newArray(int size) {
            return (new DetectedImage[size]);
        }

    }
            ;

    protected DetectedImage(Parcel in) {
        this.encryption = ((Encryption) in.readValue((Encryption.class.getClassLoader())));
        this.streamImageURI = ((String) in.readValue((String.class.getClassLoader())));
        this.imageData = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetectedImage() {
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public String getStreamImageURI() {
        return streamImageURI;
    }

    public void setStreamImageURI(String streamImageURI) {
        this.streamImageURI = streamImageURI;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(encryption);
        dest.writeValue(streamImageURI);
        dest.writeValue(imageData);
    }

    public int describeContents() {
        return 0;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}