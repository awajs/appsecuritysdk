package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 5/7/2020.
 */
public class VersionControl implements Parcelable
{

    @SerializedName("DateTimeStamp")
    @Expose
    private String dateTimeStamp;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("VersionNumber")
    @Expose
    private Integer versionNumber;
    public final static Creator<VersionControl> CREATOR = new Creator<VersionControl>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VersionControl createFromParcel(Parcel in) {
            return new VersionControl(in);
        }

        public VersionControl[] newArray(int size) {
            return (new VersionControl[size]);
        }

    }
            ;

    protected VersionControl(Parcel in) {
        this.dateTimeStamp = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.versionNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public VersionControl() {
    }

    /**
     *
     * @param dateTimeStamp
     * @param nodeID
     * @param versionNumber
     */
    public VersionControl(String dateTimeStamp, String nodeID, Integer versionNumber) {
        super();
        this.dateTimeStamp = dateTimeStamp;
        this.nodeID = nodeID;
        this.versionNumber = versionNumber;
    }

    public String getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(String dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTimeStamp);
        dest.writeValue(nodeID);
        dest.writeValue(versionNumber);
    }

    public int describeContents() {
        return 0;
    }

}
