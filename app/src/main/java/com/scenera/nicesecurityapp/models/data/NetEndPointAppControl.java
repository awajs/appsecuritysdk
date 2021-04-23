package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class NetEndPointAppControl implements Parcelable
{


    @SerializedName("APIVersion")
    @Expose
    private String aPIVersion;
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    @SerializedName("Scheme")
    @Expose
    private List<SchemeAppControlObject> schemeAppControlObject = null;
    public final static Creator<NetEndPointAppControl> CREATOR = new Creator<NetEndPointAppControl>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NetEndPointAppControl createFromParcel(Parcel in) {
            return new NetEndPointAppControl(in);
        }

        public NetEndPointAppControl[] newArray(int size) {
            return (new NetEndPointAppControl[size]);
        }

    }
            ;

    protected NetEndPointAppControl(Parcel in) {
        this.aPIVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.endPointID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.schemeAppControlObject, ( SchemeAppControlObject.class.getClassLoader()));
    }

    public NetEndPointAppControl() {
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

    public List<SchemeAppControlObject> getSchemeAppControlObject() {
        return schemeAppControlObject;
    }

    public void setSchemeAppControlObject(List<SchemeAppControlObject> schemeAppControlObject) {
        this.schemeAppControlObject = schemeAppControlObject;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aPIVersion);
        dest.writeValue(endPointID);
        dest.writeList(schemeAppControlObject);
    }

    public int describeContents() {
        return 0;
    }


}