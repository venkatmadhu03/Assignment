package com.office24by7.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //create View Objects
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    //create Adapte object
    ListAdapter listAdapter;

    //Progress Dialog
    ProgressDialog progressDialog;

    //ViewModel
    CountryDetailsViewModel countryDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = showProgressDialog(this);

        //intialize Views
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        //initialize LayoutManager and attach it to RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //Get reference from ViewModel and display the data here
        countryDetailsViewModel = ViewModelProviders.of(this).get(CountryDetailsViewModel.class);
        getListFromServer();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getListFromServer();
                        swipeRefresh.setRefreshing(false);
                    }
                }, 2000);

            }
        });
    }

    private void getListFromServer(){
        countryDetailsViewModel.getDetails().observe(this, new Observer<List<ListValuesModel>>() {
            @Override
            public void onChanged(List<ListValuesModel> entityModels) {
                listAdapter = new ListAdapter(entityModels, MainActivity.this);
                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
            }
        });
    }


    public static ProgressDialog showProgressDialog(Context mContext) {

        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please wait While Connecting");
        progressDialog.setCancelable(false);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;

    }// end of Progress dialog
}
