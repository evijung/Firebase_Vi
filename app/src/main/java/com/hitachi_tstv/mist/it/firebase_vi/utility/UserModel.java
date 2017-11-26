package com.hitachi_tstv.mist.it.firebase_vi.utility;

/**
 * Created by Tunyaporn on 11/26/2017.
 */

public class UserModel {
    private String uidSting,nameDisplayString;

    public UserModel() {

    }// For Getter

    public UserModel(String uidSting, String nameDisplayString) {
        this.uidSting = uidSting;
        this.nameDisplayString = nameDisplayString;
    } // For Setter

    public String getUidSting() {
        return uidSting;
    }

    public String getNameDisplayString() {
        return nameDisplayString;
    }

    public void setUidSting(String uidSting) {
        this.uidSting = uidSting;
    }

    public void setNameDisplayString(String nameDisplayString) {
        this.nameDisplayString = nameDisplayString;
    }
}// Main Class


