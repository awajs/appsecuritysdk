package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class ParentSceneMark implements Parcelable
{

    @SerializedName("SceneMarkID")
    @Expose
    private String sceneMarkID;
    @SerializedName("VersionNumber")
    @Expose
    private Integer versionNumber;
    public final static Creator<ParentSceneMark> CREATOR = new Creator<ParentSceneMark>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ParentSceneMark createFromParcel(Parcel in) {
            return new ParentSceneMark(in);
        }

        public ParentSceneMark[] newArray(int size) {
            return (new ParentSceneMark[size]);
        }

    }
            ;

    protected ParentSceneMark(Parcel in) {
        this.sceneMarkID = ((String) in.readValue((String.class.getClassLoader())));
        this.versionNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public ParentSceneMark() {
    }

    /**
     *
     * @param sceneMarkID
     * @param versionNumber
     */
    public ParentSceneMark(String sceneMarkID, Integer versionNumber) {
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
