package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Output implements Parcelable {
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("PortID")
    @Expose
    private String portID;
    @SerializedName("DestinationEndPointList")
    @Expose
    private List<ControlEndPoint> destinationEndPointList = null;
    @SerializedName("Encryption")
    @Expose
    private Encryption encryption;

    protected Output(Parcel in) {
        type = in.readString();
        portID = in.readString();
        destinationEndPointList = in.createTypedArrayList(ControlEndPoint.CREATOR);
        this.encryption = ((Encryption) in.readValue((Encryption.class.getClassLoader())));
    }

    public static final Creator<Output> CREATOR = new Creator<Output>() {
        @Override
        public Output createFromParcel(Parcel in) {
            return new Output(in);
        }

        @Override
        public Output[] newArray(int size) {
            return new Output[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public List<ControlEndPoint> getDestinationEndPointList() {
        return destinationEndPointList;
    }

    public void setDestinationEndPointList(List<ControlEndPoint> destinationEndPointList) {
        this.destinationEndPointList = destinationEndPointList;
    }

    public Encryption getEncryption() {
        return encryption;
    }

    public void setEncryption(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(portID);
        dest.writeTypedList(destinationEndPointList);
        dest.writeValue(encryption);
    }
}
