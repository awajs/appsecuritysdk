package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by Ekta Bhatt on 28,November,2019
 */
public class NodeList implements Parcelable
{
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected = false;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("ImageURL")
    @Expose
    private String imageURL;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TimeZone")
    @Expose
    private String timeZone;
    @SerializedName("isTimeZoneSelected")
    @Expose
    private Boolean isTimeZoneSelected = false;
    public final static Creator<NodeList> CREATOR = new Creator<NodeList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NodeList createFromParcel(Parcel in) {
            return new NodeList(in);
        }

        public NodeList[] newArray(int size) {
            return (new NodeList[size]);
        }

    }
            ;

    protected NodeList(Parcel in) {
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((String.class.getClassLoader())));
        this.imageURL = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.timeZone = ((String) in.readValue((String.class.getClassLoader())));
        this.isSelected = ((Boolean)in.readValue((Boolean.class.getClassLoader())));
        this.isTimeZoneSelected = ((Boolean)in.readValue((Boolean.class.getClassLoader())));
    }

    public NodeList() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(nodeID);
        dest.writeValue(imageURL);
        dest.writeValue(status);
        dest.writeValue(timeZone);
        dest.writeValue(isSelected);
        dest.writeValue(isTimeZoneSelected);
    }

    public int describeContents() {
        return 0;
    }
    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
    public Boolean getTimezoneSelected() {
        return isTimeZoneSelected;
    }

    public void setTimezoneSelected(Boolean timezoneSelected) {
        isTimeZoneSelected = timezoneSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeList that = (NodeList) o;
        return nodeID.equals(that.nodeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeID);
    }

    @Override
    public String toString() {
        return "NodeList{" +
                "isSelected=" + isSelected +
                ", description='" + description + '\'' +
                ", nodeID='" + nodeID + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", status='" + status + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", isTimeZoneSelected=" + isTimeZoneSelected +
                '}';
    }
}
