package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SceneMarkOutputEndPointForDevice implements Parcelable {

    @SerializedName("APIVersion")
    @Expose
    private String APIVersion;


    @SerializedName("EndPointID")
    @Expose
    private String EndPointID;


    @SerializedName("NodeID")
    @Expose
    private String NodeID;


    @SerializedName("PortID")
    @Expose
    private String PortID;


    @SerializedName("Scheme")
    @Expose
    private List<SchemeAppSecurity> scheme = null;

    public String getAPIVersion() {
        return APIVersion;
    }

    public void setAPIVersion(String APIVersion) {
        this.APIVersion = APIVersion;
    }

    public String getEndPointID() {
        return EndPointID;
    }

    public void setEndPointID(String endPointID) {
        EndPointID = endPointID;
    }

    public String getNodeID() {
        return NodeID;
    }

    public void setNodeID(String nodeID) {
        NodeID = nodeID;
    }

    public String getPortID() {
        return PortID;
    }

    public void setPortID(String portID) {
        PortID = portID;
    }

    public List<SchemeAppSecurity> getScheme() {
        return scheme;
    }

    public void setScheme(List<SchemeAppSecurity> scheme) {
        this.scheme = scheme;
    }

    protected SceneMarkOutputEndPointForDevice(Parcel in) {
        APIVersion = in.readString();
        EndPointID = in.readString();
        NodeID = in.readString();
        PortID = in.readString();
        scheme = in.createTypedArrayList(SchemeAppSecurity.CREATOR);
    }

    public static final Creator<SceneMarkOutputEndPointForDevice> CREATOR = new Creator<SceneMarkOutputEndPointForDevice>() {
        @Override
        public SceneMarkOutputEndPointForDevice createFromParcel(Parcel in) {
            return new SceneMarkOutputEndPointForDevice(in);
        }

        @Override
        public SceneMarkOutputEndPointForDevice[] newArray(int size) {
            return new SceneMarkOutputEndPointForDevice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(APIVersion);
        dest.writeValue(EndPointID);
        dest.writeValue(NodeID);
        dest.writeValue(PortID);
        dest.writeList(scheme);
    }
}
