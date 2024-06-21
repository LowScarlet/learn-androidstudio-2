package id.my.lowscarlet.uts_native;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    // TODO Edit This Dear
    public static String baseUrl = "https://a6b7-2001-448a-1082-b186-7d49-fe0-68bf-5b80.ngrok-free.app";
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

