package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class ThumbnailList implements Parcelable
{
    @SerializedName("SceneDataID")
    @Expose
    private String sceneDataID;
    @SerializedName("VersionNumber")
    @Expose
    private Integer versionNumber;
    public final static Creator<ThumbnailList> CREATOR = new Creator<ThumbnailList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ThumbnailList createFromParcel(Parcel in) {
            return new ThumbnailList(in);
        }

        public ThumbnailList[] newArray(int size) {
            return (new ThumbnailList[size]);
        }

    }
            ;

    protected ThumbnailList(Parcel in) {
        this.sceneDataID = ((String) in.readValue((String.class.getClassLoader())));
        this.versionNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ThumbnailList() {
    }

    public String getSceneDataID() {
        return sceneDataID;
    }

    public void setSceneDataID(String sceneDataID) {
        this.sceneDataID = sceneDataID;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sceneDataID);
        dest.writeValue(versionNumber);
    }

    public int describeContents() {
        return 0;
    }


}