package id.my.lowscarlet.uts_native.activity.degree;

import java.util.Map;

import id.my.lowscarlet.uts_native.response.GetAllResponse;
import id.my.lowscarlet.uts_native.response.GetResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Handler {
    // TODO Edit This Scarlet
    @POST("/api/rest/degrees/")
    Call<GetResponse<Model>> createData(@Body Model model);
    @GET("/api/rest/degrees/")
    Call<GetAllResponse<Model>> getAllData();
    @PUT("/api/rest/degrees/{id}")
    Call<GetResponse<Model>> updateData(@Path("id") String id, @Body Map<String, Object> data);
    @DELETE("/api/rest/degrees/{id}")
    Call<GetResponse<Model>> deleteData(@Path("id") String id);
}
