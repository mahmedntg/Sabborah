package com.example.company.sabborah.models;

/**
 * Created by Mohamed Sayed on 2/16/2018.
 */

public class TimeSlot {
    private int id;
    private String name;
    private boolean checked;

    public TimeSlot(int id, String name, boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
