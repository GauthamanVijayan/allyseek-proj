package com.example.hp.allyseek.parser;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryParser
{
    public static String[] country_name;
    public static String[] counrty_id;
    public static String[] country_phonecode;
    public  static String phonetemp;

    public JSONArray countryList=null;

    private String json;

    public CountryParser(String json)
    {
        this.json=json;
    }






    public void parsejson()
    {
        try {
            countryList= new JSONArray(json);
            country_name=new String[countryList.length()];
            counrty_id=new String[countryList.length()];
            country_phonecode=new String[countryList.length()];

            for(int i=0;i<countryList.length();i++)
            {
                JSONObject jsonObject=countryList.getJSONObject(i);
                country_name[i]=jsonObject.getString("name");
                country_phonecode[i]=jsonObject.getString("phonecode");
                counrty_id[i]=jsonObject.getString("id");


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
