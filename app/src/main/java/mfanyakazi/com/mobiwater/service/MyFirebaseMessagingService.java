package mfanyakazi.com.mobiwater.service;




import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mfanyakazi.com.mobiwater.MainApplication;
import mfanyakazi.com.mobiwater.NotifyController;
import mfanyakazi.com.mobiwater.R;
import mfanyakazi.com.mobiwater.model.TokenMessage;
import mfanyakazi.com.mobiwater.model.TokenResponse;
import mfanyakazi.com.mobiwater.utils.PrefUtils;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage message) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + message.getFrom());



        // Check if message contains a data payload.
        if (message.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " +  message.getData().get("url"));

            Gson gson = new Gson();
            handleNow(message);


        }

        // Check if message contains a notification payload.
        if (message.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + message.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }
    public class DataObject{
        @SerializedName("url")
        @Expose
        private String url;
        public DataObject(){}

        public String getUrl() {
            return url;
        }

        public void setUrl(String url){
            this.url = url;
        }
    }

    public void handleNow(RemoteMessage message){
        Log.e("handle now", "about to notify");

        String url = message.getData().get("url");
        String info = message.getData().get("info");
        NotifyController notifyController = new NotifyController(getApplicationContext(), url, info);
        notifyController.tankNotification();
    }

    @Override
    public void onNewToken(String str){
        super.onNewToken(str);
        Log.e("NEW_TOKEN", str);
        PrefUtils prefUtils = new PrefUtils(getApplicationContext());
        prefUtils.setAppToken(str);


    }




}
