package com.mobileproto.lab5;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/26/13.
 */
public class SearchFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.search_fragment, null);

        //making an ArrayList for the feed
        List<FeedItem> Data = new ArrayList<FeedItem>();

        // Set up the ArrayAdapter for the feedList
        final FeedListAdapter feedListAdapter = new FeedListAdapter(this.getActivity(), Data);
        ListView feedList = (ListView) v.findViewById(R.id.searchResults);
        feedList.setAdapter(feedListAdapter);

        Button searchButton = (Button) v.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView keyword = (TextView) v.findViewById(R.id.searchField);
                String url = "http://twitterproto.herokuapp.com/tweets?q=" + keyword.getText();
                Log.d("royalsss", url);

                new ServerRequest(feedListAdapter).execute(url);
            }
        });

        return v;
    }

}
