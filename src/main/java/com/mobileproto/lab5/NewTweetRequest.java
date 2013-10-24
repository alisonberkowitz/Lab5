package com.mobileproto.lab5;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alison on 10/23/13.
 */
public class NewTweetRequest extends AsyncTask<String, Integer, List<FeedItem>> {
    //save feedlistadapter as a property of server request

    //getting data from the intranets

    public NewTweetRequest(){
        //assigning the inputs to stuff in here

    }

    protected void onPostExecute(List<FeedItem> result){

    }

    @Override
    protected List<FeedItem> doInBackground(String... uri){
        //url to make request
        String url = uri[0];
        String tweet = uri[1];

        //JSON Node names
        String TAG_TWEETS = "tweets";
        String TAG_TWEET = "tweet";
        String TAG_USERNAME = "username";
        String TAG_DATE = "date";
        String TAG_ID = "_id";

        // tweets JSONArray
        JSONArray tweets = null;

        //Creating JSON Parser instance
        NewTweetParser jParser = new NewTweetParser();

        //geting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url, "tweet", tweet);

        //making an ArrayList for the feed
        List<FeedItem> Data = new ArrayList<FeedItem>();

        return Data;
    }
}
