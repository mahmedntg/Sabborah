package com.example.company.sabborah.responses;

import com.example.company.sabborah.responses.tutor.Subject;
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;

import java.util.List;

/**
 * Created by Mohamed Sayed on 2/3/2018.
 */

public class MultiGrade extends MultiCheckExpandableGroup {
    public MultiGrade(String title, List<Subject> items) {
        super(title, items);
    }
}