package com.scenera.nicesecurityapp.models.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 23-Sep-2020
 */
public class NICEASEndPointAppSecurity implements Parcelable
{

    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppSecurity appEndPoint;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppSecurity netEndPoint;
    public final static Creator<NICEASEndPointAppSecurity> CREATOR = new Creator<NICEASEndPointAppSecurity>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NICEASEndPointAppSecurity createFromParcel(Parcel in) {
            return new NICEASEndPointAppSecurity(in);
        }

        public NICEASEndPointAppSecurity[] newArray(int size) {
            return (new NICEASEndPointAppSecurity[size]);
        }

    }
            ;

    protected NICEASEndPointAppSecurity(Parcel in) {
        this.appEndPoint = ((AppEndPointAppSecurity) in.readValue((AppEndPointAppSecurity.class.getClassLoader())));
        this.netEndPoint = ((NetEndPointAppSecurity) in.readValue((NetEndPointAppSecurity.class.getClassLoader())));
    }

    public NICEASEndPointAppSecurity() {
    }

    public AppEndPointAppSecurity getAppEndPoint() {
        return appEndPoint;
    }

    public void setAppEndPoint(AppEndPointAppSecurity appEndPoint) {
        this.appEndPoint = appEndPoint;
    }

    public NetEndPointAppSecurity getNetEndPoint() {
        return netEndPoint;
    }

    public void setNetEndPoint(NetEndPointAppSecurity netEndPoint) {
        this.netEndPoint = netEndPoint;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appEndPoint);
        dest.writeValue(netEndPoint);
    }

    public int describeContents() {
        return 0;
    }

}
