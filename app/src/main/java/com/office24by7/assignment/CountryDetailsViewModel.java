package com.office24by7.assignment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailsViewModel extends ViewModel {

    private MutableLiveData<List<ListValuesModel>> listMutableLiveData;
    private RetroClient retroClient;
    String title;

    public LiveData<List<ListValuesModel>> getDetails() {
        //if the list is null
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<List<ListValuesModel>>();
            //we will load it asynchronously from server in this method
            loadCountryDetails();
        }

        //finally we will return the list
        return listMutableLiveData;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadCountryDetails() {
        retroClient = APIService.getRetrofitInstance().create(RetroClient.class);
        Call<EntityModel> call = retroClient.getCountryDetails();

        call.enqueue(new Callback<EntityModel>() {
            @Override
            public void onResponse(Call<EntityModel> call, Response<EntityModel> response) {
                EntityModel entityModel = response.body();
                Log.d("Response", "onSuccess: "+response.body().getTitle());
                listMutableLiveData.setValue(entityModel.getRows());
                setTitle(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<EntityModel> call, Throwable t) {
                Log.d("Response", "onFailure: "+t.getMessage());
            }
        });
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}
