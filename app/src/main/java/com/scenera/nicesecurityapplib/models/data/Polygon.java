package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Polygon implements Parcelable {
    @SerializedName("x")
    @Expose
    private double x;
    @SerializedName("y")
    @Expose
    private double y;

    protected Polygon(Parcel in) {
        x = in.readDouble();
        y = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(x);
        dest.writeDouble(y);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Polygon> CREATOR = new Creator<Polygon>() {
        @Override
        public Polygon createFromParcel(Parcel in) {
            return new Polygon(in);
        }

        @Override
        public Polygon[] newArray(int size) {
            return new Polygon[size];
        }
    };

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
