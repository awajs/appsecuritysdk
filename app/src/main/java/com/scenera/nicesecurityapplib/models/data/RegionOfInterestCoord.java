package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionOfInterestCoord implements Parcelable {
    @SerializedName("Polygon")
    @Expose
    private List<Polygon> polygon = null;

    protected RegionOfInterestCoord(Parcel in) {
        polygon = in.createTypedArrayList(Polygon.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(polygon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegionOfInterestCoord> CREATOR = new Creator<RegionOfInterestCoord>() {
        @Override
        public RegionOfInterestCoord createFromParcel(Parcel in) {
            return new RegionOfInterestCoord(in);
        }

        @Override
        public RegionOfInterestCoord[] newArray(int size) {
            return new RegionOfInterestCoord[size];
        }
    };

    public List<Polygon> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<Polygon> polygon) {
        this.polygon = polygon;
    }
}
