package mfanyakazi.com.mobiwater.utils;



import mfanyakazi.com.mobiwater.model.TokenMessage;
import mfanyakazi.com.mobiwater.model.TokenResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by choks on 24/01/2017.
 */

public interface NetworkAPI {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("json.php")
    Observable<Response<String>> sendToken(@Body TokenMessage data);

}

