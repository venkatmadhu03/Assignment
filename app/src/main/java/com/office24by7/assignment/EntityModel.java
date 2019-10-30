package com.office24by7.assignment;


import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {

    String title="";
    List<ListValuesModel> rows = new ArrayList<>();


    public EntityModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListValuesModel> getRows() {
        return rows;
    }

    public void setRows(List<ListValuesModel> rows) {
        this.rows = rows;
    }
}
