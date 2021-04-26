package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 2/11/2020.
 */
public class ReturnTotalSceneMarks implements Parcelable
{

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
    private List<String> nICEItemTypesPresent = null;
    @SerializedName("SceneMarkList")
    @Expose
    private Object sceneMarkList;
    public final static Creator<ReturnTotalSceneMarks> CREATOR = new Creator<ReturnTotalSceneMarks>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ReturnTotalSceneMarks createFromParcel(Parcel in) {
            return new ReturnTotalSceneMarks(in);
        }

        public ReturnTotalSceneMarks[] newArray(int size) {
            return (new ReturnTotalSceneMarks[size]);
        }

    }
            ;

    protected ReturnTotalSceneMarks(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.totalNumberOfSceneMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.nICEItemTypesPresent, (String.class.getClassLoader()));
        this.sceneMarkList = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public ReturnTotalSceneMarks() {
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

    public List<String> getNICEItemTypesPresent() {
        return nICEItemTypesPresent;
    }

    public void setNICEItemTypesPresent(List<String> nICEItemTypesPresent) {
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
        dest.writeList(nICEItemTypesPresent);
        dest.writeValue(sceneMarkList);
    }

    public int describeContents() {
        return 0;
    }


}