package com.scenera.nicesecurityapp.models.response;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapp.models.data.NodeList;

import java.util.List;


/**
 * Created by Ekta Bhatt on 28,November,2019
 */
public class GetDevicesResponse implements Parcelable
{


    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("NodeList")
    @Expose
    private List<NodeList> nodeList = null;
    public final static Creator<GetDevicesResponse> CREATOR = new Creator<GetDevicesResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetDevicesResponse createFromParcel(Parcel in) {
            return new GetDevicesResponse(in);
        }

        public GetDevicesResponse[] newArray(int size) {
            return (new GetDevicesResponse[size]);
        }

    }
            ;

    protected GetDevicesResponse(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.nodeList, (NodeList.class.getClassLoader()));
    }

    public GetDevicesResponse() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public List<NodeList> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<NodeList> nodeList) {
        this.nodeList = nodeList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(accountID);
        dest.writeList(nodeList);
    }

    public int describeContents() {
        return 0;
    }
}
