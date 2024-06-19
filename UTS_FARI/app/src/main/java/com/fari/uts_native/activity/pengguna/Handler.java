package com.fari.uts_native.activity.pengguna;

import java.util.Map;

import com.fari.uts_native.response.GetAllResponse;
import com.fari.uts_native.response.GetResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Handler {
    // TODO Edit This Scarlet
    @POST("/api/rest/pengguna/")
    Call<GetResponse<Model>> createData(@Body Model model);
    @GET("/api/rest/pengguna/")
    Call<GetAllResponse<Model>> getAllData();
    @PUT("/api/rest/pengguna/{id}")
    Call<GetResponse<Model>> updateData(@Path("id") String id, @Body Map<String, Object> data);
    @DELETE("/api/rest/pengguna/{id}")
    Call<GetResponse<Model>> deleteData(@Path("id") String id);
}
