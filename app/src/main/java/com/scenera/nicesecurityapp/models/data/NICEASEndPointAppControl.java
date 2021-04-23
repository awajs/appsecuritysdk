package com.scenera.nicesecurityapp.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 21-Sep-2020
 */
public class NICEASEndPointAppControl implements Parcelable
{

    @SerializedName("AppEndPoint")
    @Expose
    private AppEndPointAppControl appEndPointAppControl;
    @SerializedName("NetEndPoint")
    @Expose
    private NetEndPointAppControl netEndPointAppControl;
    public final static Creator<NICEASEndPointAppControl> CREATOR = new Creator<NICEASEndPointAppControl>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NICEASEndPointAppControl createFromParcel(Parcel in) {
            return new NICEASEndPointAppControl(in);
        }

        public NICEASEndPointAppControl[] newArray(int size) {
            return (new NICEASEndPointAppControl[size]);
        }

    }
            ;

    protected NICEASEndPointAppControl(Parcel in) {
        this.appEndPointAppControl = ((AppEndPointAppControl) in.readValue((AppEndPointAppControl.class.getClassLoader())));
        this.netEndPointAppControl = ((NetEndPointAppControl) in.readValue((NetEndPointAppControl.class.getClassLoader())));
    }

    public NICEASEndPointAppControl() {
    }

    public AppEndPointAppControl getAppEndPointAppControl() {
        return appEndPointAppControl;
    }

    public void setAppEndPointAppControl(AppEndPointAppControl appEndPointAppControl) {
        this.appEndPointAppControl = appEndPointAppControl;
    }

    public NetEndPointAppControl getNetEndPointAppControl() {
        return netEndPointAppControl;
    }

    public void setNetEndPointAppControl(NetEndPointAppControl netEndPointAppControl) {
        this.netEndPointAppControl = netEndPointAppControl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appEndPointAppControl);
        dest.writeValue(netEndPointAppControl);
    }

    public int describeContents() {
        return 0;
    }

}
