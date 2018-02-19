package com.example.company.sabborah.responses;

/**
 * Created by Mohamed Sayed on 2/18/2018.
 */

public class UserInformation {
    private String uid;
    private boolean isTutor;
    private String email;

    public UserInformation(String uid, boolean isTutor, String email) {
        this.uid = uid;
        this.isTutor = isTutor;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
