package mfanyakazi.com.mobiwater.service;




import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        System.out.print("firebase notification "+message.getNotification().getBody());
    }
}
