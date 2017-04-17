package com.example.home.think_natalia;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 4/17/2017.
 */

public class Json {
    //List<String> links = new ArrayList<>();

    public List jsonshow(String response)
    {
        List<String> links = new ArrayList<>();
        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray items = jsonObj.getJSONArray("items");
            for (int i = 0; i < items.length(); i++)
            {
                JSONObject c = items.getJSONObject(i);
                JSONObject images = c.getJSONObject("images");

                JSONObject url = images.getJSONObject("standard_resolution");
                String x = url.getString("url");
                Log.i("xxxxxxxxx",x);
                links.add(i,x);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return links;
    }
}
