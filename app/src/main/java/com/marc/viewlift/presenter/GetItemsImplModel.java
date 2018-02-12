package com.marc.viewlift.presenter;

import android.os.Handler;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.marc.viewlift.R;
import com.marc.viewlift.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetItemsImplModel implements GetItemsModel {
    private String TAG;
    public GetItemsImplModel() {
        TAG = getClass().getName();
    }

    private OnFinishedListener listener;
    private List<Movies> list;
    private List<List<Movies>> mainList = new ArrayList<>();
    @Override
    public void getItems(OnFinishedListener listener, final RequestQueue queue, final String url) {
        Log.d(TAG,"getItems()");
        fetchData(queue, url);
        this.listener = listener;
    }

    private void fetchData(RequestQueue queue, String url) {
        Log.d(TAG, "fetchData()");
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // set number of request to user
                parseJsonFeed(response);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.d(TAG,"onErrorResponse() ");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFinishedError("Error Loading content.");
                    }
                }, 0);

            }
        });

        // Adding request to volley request queue
        queue.add(strReq);

    }
    private void parseJsonFeed(String result) {
        Log.d(TAG, "parseJsonFeed()");

        String title;
        String tags;
        String permaLink;
        String image;
        try {
            JSONObject jsonObject = new JSONObject(result);
              JSONObject obj = jsonObject.getJSONObject("films");
            // Getting JSON Array node
            JSONArray jsonArray = obj.getJSONArray("film");
            for (int i=0;i<jsonArray.length();i++) {
                list = new ArrayList<>();

                JSONObject film = jsonArray.getJSONObject(i);
                title = film.getString("title");
                tags = film.getString("tags");
                permaLink = film.getString("permaLink");
                Log.d(TAG,title);

                //String primaryCategory = film.getString("primaryCategory");
                //String secondaryCategories = film.getString("secondaryCategories");
                String images = film.getString("images");
                image = new JSONObject(images)
                        .getJSONArray("image")
                        .getJSONObject(0)
                        .getString("src");
                String relatedFilms = film.getString("relatedFilms");

                Movies movies = new Movies(title,tags,permaLink,image);
                list.add(movies);

                parseJsonFeedRelated(relatedFilms);

                mainList.add(list);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,mainList.toString());
                listener.onFinishedList(mainList);
            }
        }, 0);
    }

    private void parseJsonFeedRelated(String relatedFilms) {
        Log.d(TAG, "parseJsonFeedRelated()");
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(relatedFilms);
            JSONArray jsonArray = jsonObject.getJSONArray("relatedFilm");
            for (int i=0;i<jsonArray.length();i++) {
                String title = jsonArray.getJSONObject(i).getString("title");
                String tags = "";
                String permaLink = "";
                String images = jsonArray.getJSONObject(i).getString("images");
                String image = new JSONObject(images)
                        .getJSONArray("image")
                        .getJSONObject(0)
                        .getString("src");
                Movies movies = new Movies(title,tags,permaLink,image);
                list.add(movies);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
