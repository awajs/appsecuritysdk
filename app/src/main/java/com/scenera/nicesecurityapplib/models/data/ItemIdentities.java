package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemIdentities implements Parcelable {
    @SerializedName("ItemIDs")
    @Expose
    private List<String> itemIDs = null;
    @SerializedName("ItemType")
    @Expose
    private String itemType;

    protected ItemIdentities(Parcel in) {
        itemIDs = in.createStringArrayList();
        itemType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(itemIDs);
        dest.writeString(itemType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemIdentities> CREATOR = new Creator<ItemIdentities>() {
        @Override
        public ItemIdentities createFromParcel(Parcel in) {
            return new ItemIdentities(in);
        }

        @Override
        public ItemIdentities[] newArray(int size) {
            return new ItemIdentities[size];
        }
    };

    public List<String> getItemIDs() {
        return itemIDs;
    }

    public void setItemIDs(List<String> itemIDs) {
        this.itemIDs = itemIDs;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
