package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class SceneData implements Parcelable
{

    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("Encryption")
    @Expose
    private Encryption encryption;
    @SerializedName("MediaFormat")
    @Expose
    private String mediaFormat;
    @SerializedName("aSceneDataURI")
    @Expose
    private String sceneDataURI;
    public final static Creator<SceneData> CREATOR = new Creator<SceneData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SceneData createFromParcel(Parcel in) {
            return new SceneData(in);
        }

        public SceneData[] newArray(int size) {
            return (new SceneData[size]);
        }

    }
            ;

    protected SceneData(Parcel in) {
        this.dataType = ((String) in.readValue((String.class.getClassLoader())));
        this.encryption = ((Encryption) in.readValue((Encryption.class.getClassLoader())));
        this.mediaFormat = ((String) in.readValue((String.class.getClassLoader())));
        this.sceneDataURI = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SceneData() {
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    public String getMediaFormat() {
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public String getSceneDataURI() {
        return sceneDataURI;
    }

    public void setSceneDataURI(String sceneDataURI) {
        this.sceneDataURI = sceneDataURI;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dataType);
        dest.writeValue(encryption);
        dest.writeValue(mediaFormat);
        dest.writeValue(sceneDataURI);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "SceneData{" +
                "dataType='" + dataType + '\'' +
                ", encryption=" + encryption +
                ", mediaFormat='" + mediaFormat + '\'' +
                ", sceneDataURI='" + sceneDataURI + '\'' +
                '}';
    }
}