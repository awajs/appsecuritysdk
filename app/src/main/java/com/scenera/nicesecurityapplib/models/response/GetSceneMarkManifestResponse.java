package com.scenera.nicesecurityapplib.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.scenera.nicesecurityapplib.models.data.SceneMarkList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class GetSceneMarkManifestResponse implements Parcelable {
    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("StartDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("EndDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("PageLength")
    @Expose
    private Integer pageLength;
    @SerializedName("NICEItemTypesPresent")
    @Expose
    private Object nICEItemTypesPresent;
    @SerializedName("ListDates")
    @Expose
    private List<String> listDates = null;
    @SerializedName("SceneMarkList")
    @Expose
    private ArrayList<SceneMarkList> sceneMarkList = null;
    @SerializedName("ContinuationToken")
    @Expose
    private String continuationToken;
    @SerializedName("DeviceName")
    @Expose
    private String deviceName;
    @SerializedName("DeviceTimeZone")
    @Expose
    private String deviceTimeZone;
    public final static Creator<GetSceneMarkManifestResponse> CREATOR = new Creator<GetSceneMarkManifestResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetSceneMarkManifestResponse createFromParcel(Parcel in) {
            return new GetSceneMarkManifestResponse(in);
        }

        public GetSceneMarkManifestResponse[] newArray(int size) {
            return (new GetSceneMarkManifestResponse[size]);
        }

    }
            ;

    protected GetSceneMarkManifestResponse(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.pageLength = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nICEItemTypesPresent = ((Object) in.readValue((Object.class.getClassLoader())));
        //  in.readList(this.nICEItemTypesPresent, (java.lang.String.class.getClassLoader()));
        in.readList(this.listDates, (String.class.getClassLoader()));
        in.readList(this.sceneMarkList, (SceneMarkList.class.getClassLoader()));
        this.continuationToken = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceName = ((String) in.readValue((String.class.getClassLoader())));
        this.deviceTimeZone = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetSceneMarkManifestResponse() {
    }
    public String getDeviceTimeZone() {
        return deviceTimeZone;
    }

    public void setDeviceTimeZone(String timezone) {
        this.deviceTimeZone = timezone;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getPageLength() {
        return pageLength;
    }

    public void setPageLength(Integer pageLength) {
        this.pageLength = pageLength;
    }

    public Object getNICEItemTypesPresent() {
        return nICEItemTypesPresent;
    }

    public void setNICEItemTypesPresent(Object nICEItemTypesPresent) {
        this.nICEItemTypesPresent = nICEItemTypesPresent;
    }


    public List<String> getListDates() {
        return listDates;
    }

    public void setListDates(List<String> listDates) {
        this.listDates = listDates;
    }

    public ArrayList<SceneMarkList> getSceneMarkList() {
        return sceneMarkList;
    }

    public void setSceneMarkList(ArrayList<SceneMarkList> sceneMarkList) {
        this.sceneMarkList = sceneMarkList;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(startDateTime);
        dest.writeValue(endDateTime);
        dest.writeValue(pageLength);
        dest.writeValue(nICEItemTypesPresent);
        dest.writeList(listDates);
        dest.writeList(sceneMarkList);
        dest.writeValue(continuationToken);
        dest.writeValue(deviceName);
        dest.writeValue(deviceTimeZone);
    }

    public int describeContents() {
        return 0;
    }


}