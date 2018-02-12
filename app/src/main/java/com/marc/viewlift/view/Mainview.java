package com.marc.viewlift.view;

import com.marc.viewlift.model.Movies;

import java.util.List;

public interface Mainview {

    void showProgress();
    void setItem(List<List<Movies>> item);
    void showMessage(String message);
    void hideProgress();
}
