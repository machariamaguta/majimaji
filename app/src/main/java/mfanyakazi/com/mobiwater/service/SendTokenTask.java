package mfanyakazi.com.mobiwater.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

import mfanyakazi.com.mobiwater.utils.Constants;

public class SendTokenTask extends AsyncTask<String, Void, String> {

    private Context context;

    public SendTokenTask(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String tokenJson = arg0[0];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
//            data = "?appToken=" + URLEncoder.encode(tokenJson, "UTF-8");
            data = "?appToken=" + tokenJson;


            // link = "http://10.0.2.2:8080/androidphp/register_merchant.php" + data;
            // link = "http://testandroid.netai.net/register_merchant.php" + data;
            link = Constants.API_BASE_URL + data;
            Log.d("HTTP_LINK", link);
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (SocketTimeoutException e) {
            return "{\"query_result\":\"HTTP_TIMEOUT\"}";
        } catch (IOException e) {
            return new String("Error: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        Log.d("JSON_STR", jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");

                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "REGISTRATION: Data inserted successfully. Registration successful.", Toast
                            .LENGTH_SHORT).show();
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "REGISTRATION: Registration process failed!", Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("HTTP_TIMEOUT")) {
                    Toast.makeText(context, "HTTP request timed out. Check Internet connection!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "REGISTRATION: Couldn't connect to remote database!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "REGISTRATION: Unknown error. Error parsing JSON data!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "REGISTRATION: Unknown error. Couldn't get any JSON data!", Toast.LENGTH_SHORT).show();
        }
    }
}