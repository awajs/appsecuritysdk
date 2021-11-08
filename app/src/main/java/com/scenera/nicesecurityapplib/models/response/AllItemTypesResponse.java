package com.scenera.nicesecurityapplib.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class AllItemTypesResponse {

    @SerializedName("NICEItemTypes")
    @Expose
    private ArrayList<String> nICEItemTypes;

    @SerializedName("EventTypes")
    @Expose
    private ArrayList<String> eventTypes;

    public ArrayList<String> getnICEItemTypes() {
        return nICEItemTypes;
    }

    public void setnICEItemTypes(ArrayList<String> nICEItemTypes) {
        this.nICEItemTypes = nICEItemTypes;
    }

    public ArrayList<String> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(ArrayList<String> eventTypes) {
        this.eventTypes = eventTypes;
    }
}
