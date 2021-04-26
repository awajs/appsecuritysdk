package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekta Bhatt on 11,March,2020
 */
public class EventDates implements Parcelable
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
    @SerializedName("ListDates")
    @Expose
    private List<String> listDates = null;
    @SerializedName("SceneMarkList")
    @Expose
    private Object sceneMarkList;
    public final static Creator<EventDates> CREATOR = new Creator<EventDates>() {


        @SuppressWarnings({
                "unchecked"
        })
        public EventDates createFromParcel(Parcel in) {
            return new EventDates(in);
        }

        public EventDates[] newArray(int size) {
            return (new EventDates[size]);
        }

    }
            ;

    protected EventDates(Parcel in) {
        this.version = ((String) in.readValue((String.class.getClassLoader())));
        this.startDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.endDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.totalNumberOfSceneMarks = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.nICEItemTypesPresent, (String.class.getClassLoader()));
        in.readList(this.listDates, (String.class.getClassLoader()));
        this.sceneMarkList = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public EventDates() {
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

    public List<String> getListDates() {
        return listDates;
    }

    public void setListDates(List<String> listDates) {
        this.listDates = listDates;
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
        dest.writeList(listDates);
        dest.writeValue(sceneMarkList);
    }

    public int describeContents() {
        return 0;
    }

}
