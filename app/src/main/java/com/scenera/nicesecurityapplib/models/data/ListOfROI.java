package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfROI implements Parcelable {

    @SerializedName("ListOfTags")
    @Expose
    private List<String> listOfTags = null;
    @SerializedName("RegionOfInterestCoords")
    @Expose
    private List<RegionOfInterestCoord> regionOfInterestCoords = null;
    @SerializedName("ROIName")
    @Expose
    private String rOIName;

    protected ListOfROI(Parcel in) {
        listOfTags = in.createStringArrayList();
        regionOfInterestCoords = in.createTypedArrayList(RegionOfInterestCoord.CREATOR);
        rOIName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(listOfTags);
        dest.writeTypedList(regionOfInterestCoords);
        dest.writeString(rOIName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ListOfROI> CREATOR = new Creator<ListOfROI>() {
        @Override
        public ListOfROI createFromParcel(Parcel in) {
            return new ListOfROI(in);
        }

        @Override
        public ListOfROI[] newArray(int size) {
            return new ListOfROI[size];
        }
    };

    public List<String> getListOfTags() {
        return listOfTags;
    }

    public void setListOfTags(List<String> listOfTags) {
        this.listOfTags = listOfTags;
    }

    public List<RegionOfInterestCoord> getRegionOfInterestCoords() {
        return regionOfInterestCoords;
    }

    public void setRegionOfInterestCoords(List<RegionOfInterestCoord> regionOfInterestCoords) {
        this.regionOfInterestCoords = regionOfInterestCoords;
    }

    public String getROIName() {
        return rOIName;
    }

    public void setROIName(String rOIName) {
        this.rOIName = rOIName;
    }
}
