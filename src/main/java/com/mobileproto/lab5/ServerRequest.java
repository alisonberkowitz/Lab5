package com.mobileproto.lab5;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alison on 10/5/13.
 */
public class ServerRequest extends AsyncTask<String, Integer, List<FeedItem>> {
    //save feedlistadapter as a property of server request
    public FeedListAdapter feedListAdapter;

    //getting data from the intranets

    public ServerRequest(FeedListAdapter feedListAdapter){
        //assigning the inputs to stuff in here
        this.feedListAdapter = feedListAdapter;
    }

    protected void onPostExecute(List<FeedItem> result){
        this.feedListAdapter.addAll(result);
        this.feedListAdapter.notifyDataSetChanged();
    }

    @Override
    protected List<FeedItem> doInBackground(String... uri){
        //url to make request
        String url = uri[0];

        //JSON Node names
        String TAG_TWEETS = "tweets";
        String TAG_TWEET = "tweet";
        String TAG_USERNAME = "username";
        String TAG_DATE = "date";
        String TAG_ID = "_id";

        // tweets JSONArray
        JSONArray tweets = null;

        //Creating JSON Parser instance
        JSONParser jParser = new JSONParser();

        //geting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);

        //making an ArrayList for the feed
        List<FeedItem> Data = new ArrayList<FeedItem>();

        try {
            // Getting Array of Tweets
            Log.d("Alison", json==null?"yes":"no");
            tweets = json.getJSONArray(TAG_TWEETS);

            //looping through All Tweets
            for (int i =0; i<tweets.length(); i++){
                JSONObject t = tweets.getJSONObject(i);

                // Storing each json item in variable
                String id = t.getString(TAG_ID);
                String tweet = t.getString(TAG_TWEET);
                String username = t.getString(TAG_USERNAME);
                String date = t.getString(TAG_DATE);

                //add the tweet to the feed
                FeedItem item = new FeedItem(username,tweet);
                Data.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Data;
    }
}
