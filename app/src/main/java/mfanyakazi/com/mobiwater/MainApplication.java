package mfanyakazi.com.mobiwater;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import mfanyakazi.com.mobiwater.service.NetworkService;

public class MainApplication extends Application{

    private static final String TAG = MainApplication.class.getSimpleName();
    private NetworkService networkService;
    private AppCompatActivity currentActivity;
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        networkService=new NetworkService();
        instance = this;
    }

    public NetworkService getNetworkService() {
        return networkService;
    }
    public static synchronized MainApplication getInstance(){
        return instance;
    }
}
