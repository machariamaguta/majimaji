package mfanyakazi.com.mobiwater.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import mfanyakazi.com.mobiwater.utils.Constants;
import mfanyakazi.com.mobiwater.utils.NetworkAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static String baseUrl = Constants.API_BASE_URL;
    private NetworkAPI networkAPI;
    private Retrofit retrofit;

    public NetworkService() {
        this(baseUrl);
    }

    public NetworkService(String baseUrl) {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.connectTimeout(60, TimeUnit.SECONDS);
        b.readTimeout(60, TimeUnit.SECONDS);
        b.writeTimeout(60, TimeUnit.SECONDS);
        b.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        OkHttpClient okHttpClient = b.build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        networkAPI = retrofit.create(NetworkAPI.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public NetworkAPI getAPI() {
        return networkAPI;
    }

    public NetworkAPI getNetworkAPI(final String authToken) {
        return networkAPI;
    }
}

