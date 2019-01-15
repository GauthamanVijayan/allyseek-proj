package com.example.hp.allyseek.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityParser
{
    public static String[] city_name;
    public static String[] city_id;

    String json=null;
    public JSONArray city_list=null;


    public CityParser(String json)
    {
        this.json=json;
    }

    public void parsejson()
    {
        try {
            city_list=new JSONArray(json);
            city_name=new String[city_list.length()];
            city_id=new String[city_list.length()];
            for(int i=0;i<city_list.length();i++)
            {
                JSONObject jsonObject=city_list.getJSONObject(i);
                city_name[i]=jsonObject.getString("name");
                city_id[i]=jsonObject.getString("id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
