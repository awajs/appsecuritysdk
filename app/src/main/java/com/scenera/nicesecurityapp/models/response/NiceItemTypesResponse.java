package com.scenera.nicesecurityapp.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Ekta Bhatt on 13-Sep-2020
 */
public class NiceItemTypesResponse implements Parcelable{

    @SerializedName("Version")
    @Expose
    private String version;
    @SerializedName("StartDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("EndDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("TotalNumberOfSceneMarks")
    @Expose
    private Integer totalNumberOfSceneMarks;
    @SerializedName("NICEItemTypesPresent")
    @Expose
    private Map<String, String> nICEItemTypesPresent;
    @SerializedName("SceneMarkList")
    @Expose
    private Object sceneMarkList;
    public final static Creator<NiceItemTypesResponse> CREATOR = new Creator<NiceItemTypesResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NiceItemTypesResponse createFromParcel(Parcel in) {
            return new NiceItemTypesResponse(in);
        }

        public NiceItemTypesResponse[] newArray(int size) {
            return (new NiceItemTypesResponse[size]);
        }

    }
            ;

    protected NiceItemTypesResponse(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.totalNumberOfSceneMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nICEItemTypesPresent = (Map<String, String>) in.readValue((String.class.getClassLoader()));
        this.sceneMarkList = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public NiceItemTypesResponse() {
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

    public Integer getTotalNumberOfSceneMarks() {
        return totalNumberOfSceneMarks;
    }

    public void setTotalNumberOfSceneMarks(Integer totalNumberOfSceneMarks) {
        this.totalNumberOfSceneMarks = totalNumberOfSceneMarks;
    }

    public Map<String, String> getNICEItemTypesPresent() {
        return nICEItemTypesPresent;
    }

    public void setNICEItemTypesPresent(Map<String, String> nICEItemTypesPresent) {
        this.nICEItemTypesPresent = nICEItemTypesPresent;
    }

    public Object getSceneMarkList() {
        return sceneMarkList;
    }

    public void setSceneMarkList(Object sceneMarkList) {
        this.sceneMarkList = sceneMarkList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(version);
        dest.writeValue(startDateTime);
        dest.writeValue(endDateTime);
        dest.writeValue(totalNumberOfSceneMarks);
        dest.writeValue(nICEItemTypesPresent);
        dest.writeValue(sceneMarkList);
    }

    public int describeContents() {
        return 0;
    }
}
