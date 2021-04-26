package com.scenera.nicesecurityapplib.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NetEndPointPrivacy implements Parcelable {
    @SerializedName("APIVersion")
    @Expose
    private String aPIVersion;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("NodeID")
    @Expose
    private String nodeID;
    @SerializedName("PortID")
    @Expose
    private String portID;
    @SerializedName("Scheme")
    @Expose
    private List<SchemePrivacy> schemePrivacy = new ArrayList<>();
    public final static Creator<NetEndPointPrivacy> CREATOR = new Creator<NetEndPointPrivacy>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NetEndPointPrivacy createFromParcel(Parcel in) {
            return new NetEndPointPrivacy(in);
        }

        public NetEndPointPrivacy[] newArray(int size) {
            return (new NetEndPointPrivacy[size]);
        }

    }
            ;

    protected NetEndPointPrivacy(Parcel in) {
        this.aPIVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.endPointID = ((String) in.readValue((String.class.getClassLoader())));
        this.nodeID = ((String) in.readValue((Object.class.getClassLoader())));
        this.portID = ((String) in.readValue((Object.class.getClassLoader())));
       in.readList(this.schemePrivacy, (SchemePrivacy.class.getClassLoader()));
    }

    public NetEndPointPrivacy() {
    }

    public String getAPIVersion() {
        return aPIVersion;
    }

    public void setAPIVersion(String aPIVersion) {
        this.aPIVersion = aPIVersion;
    }

    public String getEndPointID() {
        return endPointID;
    }

    public void setEndPointID(String endPointID) {
        this.endPointID = endPointID;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getPortID() {
        return portID;
    }

    public void setPortID(String portID) {
        this.portID = portID;
    }

    public List<SchemePrivacy> getScheme() {
        return schemePrivacy;
    }

    public void setScheme(List<SchemePrivacy> schemePrivacy) {
        this.schemePrivacy = schemePrivacy;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aPIVersion);
        dest.writeValue(endPointID);
        dest.writeValue(nodeID);
        dest.writeValue(portID);
        dest.writeList(schemePrivacy);
    }

    public int describeContents() {
        return 0;
    }

}
