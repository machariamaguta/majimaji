package mfanyakazi.com.mobiwater.service;

import android.content.Context;
import android.net.CaptivePortal;
import android.util.Log;

import mfanyakazi.com.mobiwater.MainApplication;
import mfanyakazi.com.mobiwater.model.TokenMessage;
import mfanyakazi.com.mobiwater.model.TokenResponse;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SendToken {
    private NetworkService mService;
    private CompositeSubscription subscriptions;
    private Context mContext;
    public SendToken(NetworkService service){
        this.mService = service;
        this.subscriptions = new CompositeSubscription();

    }
    public void send(String token, String phoneNumber){
        TokenMessage tokenMessage = new TokenMessage();
        tokenMessage.setToken(token);
        String[] phoneNumbers = {phoneNumber};
        tokenMessage.setPhoneNumbers(phoneNumbers);
        Observable<Response<String>> observable = mService.getAPI().sendToken(tokenMessage);
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("token send", "completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.e("token error", e.getMessage());
                        if (e.getMessage().contains("Failed to connect to")) {

                        }
                        if (e.getMessage().contains("Unable to resolve host")) {

                        }

                        System.out.println("Something wrong here " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<String> response) {

                        Log.e("post", response.body());

                    }
                });

        subscriptions.add(subscription);
    }
}
