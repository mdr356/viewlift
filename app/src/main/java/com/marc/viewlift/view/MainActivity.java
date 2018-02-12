package com.marc.viewlift.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.marc.viewlift.Config;
import com.marc.viewlift.R;
import com.marc.viewlift.adapter.Adapter;
import com.marc.viewlift.adapter.ItemClickListener;
import com.marc.viewlift.adapter.OnBottomReachedListener;
import com.marc.viewlift.model.Movies;
import com.marc.viewlift.presenter.GetItemsImplModel;
import com.marc.viewlift.presenter.presenterImp;
import com.marc.viewlift.presenter.presenter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, Mainview, View.OnClickListener {

    private String TAG;
    public MainActivity() {
        TAG = getClass().getName();
    }
    private ProgressBar progressBar;
    private RequestQueue queue;
    private Adapter mAdapter;
    private List<List<Movies>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate()");
        initDrawer(); // intializing the drawer and option items inn baseActivity

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        initData();
    }

    private void initData() {
        Log.d(TAG, "initData()");
        presenter presenter = new presenterImp(this, new GetItemsImplModel(), queue, Config.url);
        presenter.onFind();
    }

    private void initView() {
        Log.d(TAG,"initView()");
        progressBar = findViewById(R.id.progressBar);
        queue = Volley.newRequestQueue(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new Adapter(this,list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                //your code goes here
                Log.d(TAG,"setOnBottomReachedListener");

            }
        });
        mAdapter.setOnitemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Log.d(TAG,"selected: "+list.get(position));


            }
        });

    }
    
    @Override
    public void onClick(View v) {
        Log.d(TAG, "Item at tag: "+v.getTag());
        int tagInt = Integer.parseInt(v.getTag()+"");
    }

    @Override
    public void showProgress() {
        Log.d(TAG,"showProgress()");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItem(List<List<Movies>> item) {
        Log.d(TAG,"setItem()");
        list.addAll(item);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Log.d(TAG,"showMessage()");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        Log.d(TAG,"hideProgress()");

        progressBar.setVisibility(View.GONE);
    }
}
