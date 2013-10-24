package com.mobileproto.lab5;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alison on 10/9/13.
 */
public class ConnectionRequest extends AsyncTask<String, Integer, List<FeedNotification>> {
    //save feedlistadapter as a property of server request
    public ConnectionListAdapter connectionListAdapter;
    public List<FeedNotification> notifications;

    //getting data from the intranets

    public ConnectionRequest(ConnectionListAdapter connectionListAdapter, List<FeedNotification> notifications){
        //assigning the inputs to stuff in here
        this.connectionListAdapter = connectionListAdapter;
        this.notifications = notifications;
    }

    protected void onPostExecute(List<FeedNotification> result){
        this.connectionListAdapter.addAll(result);
        this.connectionListAdapter.notifyDataSetChanged();
    }

    @Override
    protected List<FeedNotification> doInBackground(String... uri){
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
        List<FeedNotification> notifications = new ArrayList<FeedNotification>();

        try {
            // Getting Array of Tweets
            Log.d("Alison", json == null ? "yes" : "no");
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
                MentionNotification item = new MentionNotification(username,username,tweet);
                notifications.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
