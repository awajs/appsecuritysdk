package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class ChildSceneMark implements Parcelable
{

    @SerializedName("SceneMarkID")
    @Expose
    private String sceneMarkID;
    @SerializedName("VersionNumber")
    @Expose
    private Integer versionNumber;
    public final static Creator<ChildSceneMark> CREATOR = new Creator<ChildSceneMark>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ChildSceneMark createFromParcel(Parcel in) {
            return new ChildSceneMark(in);
        }

        public ChildSceneMark[] newArray(int size) {
            return (new ChildSceneMark[size]);
        }

    }
            ;

    protected ChildSceneMark(Parcel in) {
        this.sceneMarkID = ((String) in.readValue((String.class.getClassLoader())));
        this.versionNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ChildSceneMark() {
    }

    /**
     *
     * @param sceneMarkID
     * @param versionNumber
     */
    public ChildSceneMark(String sceneMarkID, Integer versionNumber) {
        super();
        this.sceneMarkID = sceneMarkID;
        this.versionNumber = versionNumber;
    }

    public String getSceneMarkID() {
        return sceneMarkID;
    }

    public void setSceneMarkID(String sceneMarkID) {
        this.sceneMarkID = sceneMarkID;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sceneMarkID);
        dest.writeValue(versionNumber);
    }

    public int describeContents() {
        return 0;
    }

}
