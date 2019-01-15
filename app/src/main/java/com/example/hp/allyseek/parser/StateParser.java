package com.example.hp.allyseek.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StateParser
{
    public static String[] state_id;
    public static String[] state_name;

    public JSONArray stateArray=null;
    private String json;
    public  StateParser(String json)
    {
        this.json=json;
    }

    public void parsejson()
    {
        try {
            stateArray=new JSONArray(json);
            state_id=new String[stateArray.length()];
            state_name=new String[stateArray.length()];
            for(int i=0;i<stateArray.length();i++)
            {
                JSONObject jsonObject=stateArray.getJSONObject(i);
                state_id[i]=jsonObject.getString("id");
                state_name[i]=jsonObject.getString("name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
