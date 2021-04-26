package com.scenera.nicesecurityapplib.models.data;

/**
 * Created by Ekta Bhatt on 10-Sep-2020
 */

public class NICEItemTypesPresent  {

     private String name;
     private String value;

    public NICEItemTypesPresent() {
    }

    public NICEItemTypesPresent(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NICEItemTypesPresent{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
