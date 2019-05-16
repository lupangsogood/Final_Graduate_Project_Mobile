package com.autchariya.kiklik.makeuptutorial4.simulate.service;

import com.autchariya.kiklik.makeuptutorial4.simulate.model.Makeup;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Makeup_api {

    @Multipart
    @POST("apply_makeup")
    Call<Makeup> apply_makeup(@Part("type") RequestBody type , @Part("style") RequestBody style, @Part MultipartBody.Part File );



}
