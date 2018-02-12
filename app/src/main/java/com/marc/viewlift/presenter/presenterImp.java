package com.marc.viewlift.presenter;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.marc.viewlift.model.Movies;
import com.marc.viewlift.view.Mainview;

import java.util.List;


public class presenterImp implements presenter, GetItemsModel.OnFinishedListener{

    private String TAG;

    private GetItemsModel getItemsIteratorModel;
    private Mainview mainview;
    private RequestQueue queue;
    private String url;
    public presenterImp(Mainview mainview, GetItemsModel getItemsIteratorModel, RequestQueue queue, String url) {
        TAG = getClass().getName();

        Log.d(TAG, "presenterImp()");

        this.mainview = mainview;
        this.getItemsIteratorModel = getItemsIteratorModel;
        this.queue = queue;
        this.url = url;
    }

    @Override
    public void onFind() {
        Log.d(TAG, "onFind()");

        if (mainview != null) {
            mainview.showProgress();
        }
        getItemsIteratorModel.getItems(this, queue,url);
    }

    @Override
    public void onFinishedList(List<List<Movies>> items) {
        Log.d(TAG, "onFinishedList()");

        if (mainview != null) {
            mainview.setItem(items);
            mainview.hideProgress();
        }
    }

    @Override
    public void onFinishedError(String message) {
        Log.d(TAG,"onFinishedError()");

        if (mainview != null) {
            mainview.showMessage(message);
            mainview.hideProgress();
        }
    }
}
