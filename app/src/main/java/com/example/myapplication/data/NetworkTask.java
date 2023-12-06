//package com.example.myapplication.data;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapplication.R;
//import com.example.myapplication.adapter.GifAdapter;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NetworkTask {
//
//    // List to store GIF URLs
//    private  List<String> urlList = new ArrayList<>();
//
//    // Value to search for GIF
//    private final String value;
//    private final Context context;
//
//    // Constructor for NetworkTask class
//    public NetworkTask(String value, Context context) {
//        this.value = value;
//        this.context = context;
//    }
//
//    // Method to perform a request for getting GIFs
//    public List<String> getData() {
//        // Check if the value is empty, add a default GIF
//
//        // Construct the URL request with the given value
//        String url="https://api.giphy.com/v1/gifs/search?q="+ value +"&api_key=2xodbO9QyhuVZtHXXsc0JdfK1fALQ3Xj&limit=3";
//        URL obj = null;
//        try {
//            obj = new URL(url);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Create HttpURLConnection connection
//        HttpURLConnection connection = null;
//        try {
//            connection = (HttpURLConnection) obj.openConnection();
//            connection.setRequestMethod("GET"); // Set the request method to GET
//            int responseCode = connection.getResponseCode(); // Get the response code
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            // Read the response
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // Parse the JSON response to obtain GIF URLs
//            JSONObject jsonObject = new JSONObject(response.toString());
//            JSONArray dataArray = jsonObject.getJSONArray("data");
//
//            for (int i = 0; i < dataArray.length(); i++) {
//                JSONObject dataObject = dataArray.getJSONObject(i);
//                JSONObject imagesObject = dataObject.getJSONObject("images");
//                JSONObject originalObject = imagesObject.getJSONObject("original");
//                String gifUrl = originalObject.getString("url");
//                urlList.add(gifUrl);
//            }
//
//            // Add a default GIF if the list is empty
////            if (data.isEmpty()) {
////                data.add("https://media.giphy.com/media/GsHHyNG9rap4f1Rw3D/giphy.gif");
////            }
//        } catch (IOException | JSONException e) {
//            Log.i("error", "error!!");
//        }
//        return urlList;
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    public void loadMoreItems(){
//        int limit = 3;
//        int offset = urlList.size(); // Определяем смещение для запроса
//        List<String>newUrls=new ArrayList<>();
//        String url = "https://api.giphy.com/v1/gifs/search?q=" + value +
//                "&api_key=2xodbO9QyhuVZtHXXsc0JdfK1fALQ3Xj&limit=" + limit + "&offset=" + offset;
//        URL obj = null;
//        try {
//            obj = new URL(url);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        // Create HttpURLConnection connection
//        HttpURLConnection connection = null;
//        try {
//            connection = (HttpURLConnection) obj.openConnection();
//            connection.setRequestMethod("GET"); // Set the request method to GET
//            int responseCode = connection.getResponseCode(); // Get the response code
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            // Read the response
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // Parse the JSON response to obtain GIF URLs
//            JSONObject jsonObject = new JSONObject(response.toString());
//            JSONArray dataArray = jsonObject.getJSONArray("data");
//
//            for (int i = 0; i < dataArray.length(); i++) {
//                JSONObject dataObject = dataArray.getJSONObject(i);
//                JSONObject imagesObject = dataObject.getJSONObject("images");
//                JSONObject originalObject = imagesObject.getJSONObject("original");
//                String gifUrl = originalObject.getString("url");
//                newUrls.add(gifUrl);
//            }
//
//            // Add a default GIF if the list is empty
////            if (data.isEmpty()) {
////                data.add("https://media.giphy.com/media/GsHHyNG9rap4f1Rw3D/giphy.gif");
////            }
//        } catch (IOException | JSONException e) {
//            Log.i("error", "error!!");
//        }
//
//        urlList.addAll(newUrls);
//   Log.d("gghghgh",newUrls.toString());
////        RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recycle_gif);
////        GifAdapter adapter = (GifAdapter) recyclerView.getAdapter();
////        assert adapter != null;
////        ((Activity) context).runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recycle_gif);
////                GifAdapter adapter = (GifAdapter) recyclerView.getAdapter();
////                assert adapter != null;
////                adapter.notifyDataSetChanged();
////            }
////        });
//
//
//    }
//
//    // Method to update the UI using RecyclerView
//    public void updateUI() {
//        RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recycle_gif);
//        GifAdapter adapter = new GifAdapter(urlList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//    }
//}
package com.example.myapplication.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.GifAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetworkTask {
    private List<String> urlList = new ArrayList<>();
    private String value;
    private final Context context;

    public NetworkTask(String value, Context context) {
        this.value = value;
        this.context = context;
    }


    private List<String> fetchDataFromAPI(String apiUrl) {
        List<String> newUrls = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray dataArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataObject = dataArray.getJSONObject(i);
                JSONObject imagesObject = dataObject.getJSONObject("images");
                JSONObject originalObject = imagesObject.getJSONObject("original");
                String gifUrl = originalObject.getString("url");
                newUrls.add(gifUrl);
            }
        } catch (IOException | JSONException e) {
            Log.e("NetworkTask", "Error fetching data from API: " + e.getMessage());
        }

        return newUrls;
    }

    public List<String> getData() {
        String url = "https://api.giphy.com/v1/gifs/search?q=" + value + "&api_key=2xodbO9QyhuVZtHXXsc0JdfK1fALQ3Xj&limit=8";
        List<String> newUrls = fetchDataFromAPI(url);
        urlList.addAll(newUrls);
        return urlList;
    }



    public void updateUI() {
        RecyclerView recyclerView = ((Activity) context).findViewById(R.id.recycle_gif);
        GifAdapter adapter = new GifAdapter(urlList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

}
