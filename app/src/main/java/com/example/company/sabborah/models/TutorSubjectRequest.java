package com.example.company.sabborah.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mohamed Sayed on 2/9/2018.
 */

public class TutorSubjectRequest {
    @SerializedName("diff")
    @Expose
    private List<SubjectDiff> diff = null;
    @SerializedName("root")
    @Expose
    private List<SubjectAvailability> root = null;

    public List<SubjectDiff> getDiff() {
        return diff;
    }

    public void setDiff(List<SubjectDiff> diff) {
        this.diff = diff;
    }

    public List<SubjectAvailability> getRoot() {
        return root;
    }

    public void setRoot(List<SubjectAvailability> root) {
        this.root = root;
    }
}
