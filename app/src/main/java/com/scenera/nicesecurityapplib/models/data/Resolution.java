package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 15-11-2019.
 */
public class Resolution implements Parcelable
{

    @SerializedName("Height")
    @Expose
    private Integer height;
    @SerializedName("Width")
    @Expose
    private Integer width;
    public final static Creator<Resolution> CREATOR = new Creator<Resolution>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Resolution createFromParcel(Parcel in) {
            return new Resolution(in);
        }

        public Resolution[] newArray(int size) {
            return (new Resolution[size]);
        }

    }
            ;

    protected Resolution(Parcel in) {
        this.height = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.width = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Resolution() {
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(height);
        dest.writeValue(width);
    }

    public int describeContents() {
        return 0;
    }

}
