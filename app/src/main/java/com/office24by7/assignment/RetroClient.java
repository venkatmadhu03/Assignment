package com.office24by7.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetroClient {

    //"https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json"

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call <EntityModel> getCountryDetails();
}
