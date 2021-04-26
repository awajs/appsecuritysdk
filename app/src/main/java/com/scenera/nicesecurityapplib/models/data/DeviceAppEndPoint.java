package com.scenera.nicesecurityapplib.models.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekta Bhatt on 28,November,2019
 */
public class DeviceAppEndPoint implements Parcelable
{

    @SerializedName("AccessToken")
    @Expose
    private String accessToken;
    @SerializedName("NICEIdentifier")
    @Expose
    private String nICEIdentifier;
    @SerializedName("X.509Certificate")
    @Expose
    private String x509Certificate;
    public final static Creator<DeviceAppEndPoint> CREATOR = new Creator<DeviceAppEndPoint>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DeviceAppEndPoint createFromParcel(Parcel in) {
            return new DeviceAppEndPoint(in);
        }

        public DeviceAppEndPoint[] newArray(int size) {
            return (new DeviceAppEndPoint[size]);
        }

    }
            ;

    protected DeviceAppEndPoint(Parcel in) {
        this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
        this.nICEIdentifier = ((String) in.readValue((String.class.getClassLoader())));
        this.x509Certificate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DeviceAppEndPoint() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNICEIdentifier() {
        return nICEIdentifier;
    }

    public void setNICEIdentifier(String nICEIdentifier) {
        this.nICEIdentifier = nICEIdentifier;
    }

    public String getX509Certificate() {
        return x509Certificate;
    }

    public void setX509Certificate(String x509Certificate) {
        this.x509Certificate = x509Certificate;
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(nICEIdentifier);
        dest.writeValue(x509Certificate);
    }

    public int describeContents() {
        return 0;
    }

}
