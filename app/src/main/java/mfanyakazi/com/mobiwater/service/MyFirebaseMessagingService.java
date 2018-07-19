package mfanyakazi.com.mobiwater.service;




import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mfanyakazi.com.mobiwater.NotifyController;

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
        NotifyController notifyController = new NotifyController(getApplicationContext(), url);
        notifyController.tankNotification();
    }
}
