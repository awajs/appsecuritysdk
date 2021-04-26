package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonFace implements Parcelable {

    @SerializedName("personId")
    @Expose
    private String personId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Active")
    @Expose
    private boolean isActive;


    protected PersonFace(Parcel in) {
        personId = in.readString();
        name = in.readString();
        isActive = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(personId);
        dest.writeString(name);
        dest.writeByte((byte) (isActive ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonFace> CREATOR = new Creator<PersonFace>() {
        @Override
        public PersonFace createFromParcel(Parcel in) {
            return new PersonFace(in);
        }

        @Override
        public PersonFace[] newArray(int size) {
            return new PersonFace[size];
        }
    };

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
