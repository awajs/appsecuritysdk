package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekta Bhatt on 3/31/2020.
 */

public class ListNiceItemTypes implements Parcelable {

    @SerializedName("ListNiceItemTypes")
    @Expose
    private ArrayList<String> listNiceItemTypes = null;
    public final static Creator<ListNiceItemTypes> CREATOR = new Creator<ListNiceItemTypes>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ListNiceItemTypes createFromParcel(Parcel in) {
            return new ListNiceItemTypes(in);
        }

        public ListNiceItemTypes[] newArray(int size) {
            return (new ListNiceItemTypes[size]);
        }

    };

    protected ListNiceItemTypes(Parcel in) {
        in.readList(this.listNiceItemTypes, (String.class.getClassLoader()));
    }

    public ListNiceItemTypes() {
    }

    public List<String> getListNiceItemTypes() {
        return listNiceItemTypes;
    }

    public void setListNiceItemTypes(ArrayList<String> listNiceItemTypes) {
        this.listNiceItemTypes = listNiceItemTypes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(listNiceItemTypes);
    }

    public int describeContents() {
        return 0;
    }
}
