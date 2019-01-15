package com.example.hp.allyseek.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LanguageParser
{
    public static String[] language_name;
    public static String[] lanugae_id;

    public JSONArray languagelist=null;
    private String json;
    public LanguageParser(String json)
    {
        this.json=json;
    }

    public void parsejson()
    {
        try {
            languagelist=new JSONArray(json);
            language_name=new String[languagelist.length()];
            lanugae_id=new String[languagelist.length()];

            for(int i=0;i<languagelist.length();i++)
            {
                JSONObject jsonObject=languagelist.getJSONObject(i);
                language_name[i]=jsonObject.getString("language_name");
                lanugae_id[i]=jsonObject.getString("language_id");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
