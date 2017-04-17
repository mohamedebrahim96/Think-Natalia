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

    List<Item> full_object = new ArrayList<>();


    public void jsonshow(String response)
    {
        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray items = jsonObj.getJSONArray("items");
            for (int i = 0; i < items.length(); i++)
            {
                JSONObject c = items.getJSONObject(i);
                JSONObject images = c.getJSONObject("images");

                JSONObject url = images.getJSONObject("standard_resolution");
                String x = url.getString("url");
                String id = c.getString("id");
                Log.i("Json id",id);
                Item item = new Item();
                item.setImage_Url(x);
                item.setId(id);
                full_object.add(i,item);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
