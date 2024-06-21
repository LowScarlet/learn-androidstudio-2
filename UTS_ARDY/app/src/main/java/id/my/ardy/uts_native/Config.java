package id.my.ardy.uts_native;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    // TODO Edit This Dear
    public static String baseUrl = "https://2fcb-36-69-2-157.ngrok-free.app";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

