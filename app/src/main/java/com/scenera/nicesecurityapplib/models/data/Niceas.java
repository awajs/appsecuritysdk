package com.scenera.nicesecurityapplib.models.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Niceas {

    @SerializedName("NICEASEndPoint")
    @Expose
    private NICEASEndPoint nICEASEndPoint;
    @SerializedName("NICEASID")
    @Expose
    private String niceasid;
    @SerializedName("NICEASName")
    @Expose
    private String nICEASName;
    @SerializedName("RevokedJSONTokenIDs")
    @Expose
    private List<String> revokedJSONTokenIDs = null;

    public NICEASEndPoint getNICEASEndPoint() {
        return nICEASEndPoint;
    }

    public void setNICEASEndPoint(NICEASEndPoint nICEASEndPoint) {
        this.nICEASEndPoint = nICEASEndPoint;
    }

    public String getNiceasid() {
        return niceasid;
    }

    public void setNiceasid(String niceasid) {
        this.niceasid = niceasid;
    }

    public String getNICEASName() {
        return nICEASName;
    }

    public void setNICEASName(String nICEASName) {
        this.nICEASName = nICEASName;
    }

    public List<String> getRevokedJSONTokenIDs() {
        return revokedJSONTokenIDs;
    }

    public void setRevokedJSONTokenIDs(List<String> revokedJSONTokenIDs) {
        this.revokedJSONTokenIDs = revokedJSONTokenIDs;
    }
}
