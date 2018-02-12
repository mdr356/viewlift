package com.marc.viewlift.presenter;

import com.android.volley.RequestQueue;
import com.marc.viewlift.model.Movies;

import java.util.List;

public interface GetItemsModel {

    interface OnFinishedListener {
        void onFinishedList(List<List<Movies>> items);
        void onFinishedError(String message);
    }

    void getItems(OnFinishedListener listener, RequestQueue queue, String url);

}
