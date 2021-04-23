package com.scenera.nicesecurityapp.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 14-Sep-2020
 */
public class AppControlError implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    public final static Creator<AppControlError> CREATOR = new Creator<AppControlError>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AppControlError createFromParcel(Parcel in) {
            return new AppControlError(in);
        }

        public AppControlError[] newArray(int size) {
            return (new AppControlError[size]);
        }

    }
            ;

    protected AppControlError(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.errorCode = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.errorMessage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AppControlError() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(errorCode);
        dest.writeValue(errorMessage);
    }

    public int describeContents() {
        return 0;
    }

}
