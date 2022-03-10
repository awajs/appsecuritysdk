package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ekta Bhatt on 28,November,2019
 */
public class NodeList implements Parcelable
{
    @SerializedName("isSelected")
    @Expose
    private boolean isSelected = false;
    @SerializedName("isNotificationEnabled")
    @Expose
    private boolean isNotificationEnabled = false;
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
    private boolean isTimeZoneSelected = false;
    @SerializedName("DeviceType")
    @Expose
    private String deviceType;
    @SerializedName("Tags")
    @Expose
    private List<String> tags;



    public NodeList() {
    }

    protected NodeList(Parcel in) {
        isSelected = in.readByte() != 0;
        isNotificationEnabled = in.readByte() != 0;
        description = in.readString();
        nodeID = in.readString();
        imageURL = in.readString();
        status = in.readString();
        timeZone = in.readString();
        isTimeZoneSelected = in.readByte() != 0;
        deviceType = in.readString();
        tags = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeByte((byte) (isNotificationEnabled ? 1 : 0));
        dest.writeString(description);
        dest.writeString(nodeID);
        dest.writeString(imageURL);
        dest.writeString(status);
        dest.writeString(timeZone);
        dest.writeByte((byte) (isTimeZoneSelected ? 1 : 0));
        dest.writeString(deviceType);
        dest.writeStringList(tags);
    }

    public static final Creator<NodeList> CREATOR = new Creator<NodeList>() {
        @Override
        public NodeList createFromParcel(Parcel in) {
            return new NodeList(in);
        }

        @Override
        public NodeList[] newArray(int size) {
            return new NodeList[size];
        }
    };

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



    public int describeContents() {
        return 0;
    }
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getNotificationEnabled() {
        return isNotificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        isNotificationEnabled = notificationEnabled;
    }

    public boolean getTimezoneSelected() {
        return isTimeZoneSelected;
    }

    public void setTimezoneSelected(boolean timezoneSelected) {
        isTimeZoneSelected = timezoneSelected;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
                "isNotificationEnabled=" + isNotificationEnabled +
                ", description='" + description + '\'' +
                ", nodeID='" + nodeID + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", status='" + status + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", isTimeZoneSelected=" + isTimeZoneSelected +
                '}';
    }
}
