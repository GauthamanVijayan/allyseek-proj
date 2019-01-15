package com.example.hp.allyseek.registration;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hp.allyseek.R;
import com.example.hp.allyseek.Urls;
import com.example.hp.allyseek.parser.CityParser;
import com.example.hp.allyseek.parser.CountryParser;
import com.example.hp.allyseek.parser.LanguageParser;
import com.example.hp.allyseek.parser.StateParser;
import com.thomashaertel.widget.MultiSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Registrationp3 extends AppCompatActivity {
    Spinner maritalStatusSpinner, motherToungeSpinner, nationalitySpinner,
            livingPlaceSpinner, residencyStatusSpinner, phoneCodeSpinner,
            grownupSpinner, mobileNumberCodeSpinner;
    Spinner kidsCountSpinner,havingKidsSpinner,stateSpinner,citySpinner;
    ArrayList<String>country_ID=new ArrayList<String>();
    ArrayList<String>state_ID=new ArrayList<String>();
    ArrayList<String>stateList;
    ArrayList<String>cityList;
    ArrayList<String>city_ID;
    int cid;
    int finid= cid-1;


    MultiSpinner languagesSpokenSpinner;
    EditText phoneNumber_ET;
    String phoneNumber_ST;
    String maritalStatusString=null;
    String stateFinalUrl;
    String cityFinalUr;
   public  int countryProgress=0,languageProgress=0;
    public String[] countryString;
    public String[] residentStatus;
    public String[] martialStatus;
    public String[] motherToungeString;
    public String[] kidsCountString;
    public String[] havingKids;
    String selectedPhoneCode;
    ArrayList<String>tempPhonelist;

    ArrayList<String> countryList =new ArrayList<String>();
    ArrayList<String>languageList=new ArrayList<>();
    ArrayList<String>phonecodeList=new ArrayList<String>();
    ProgressDialog progressDialog;
    LinearLayout haveKidsLayout,kidsLessLayout,kidsMoreLayout;
    LinearLayout stateLayout,cityLayout;
    String country;
    int selected=0;
    boolean spinnerSelected=false;
    String living;
    int sid;
    int finalsid = 0;


    private static final String TAG = "Registrationp3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationp3);
        countryList.add(String.valueOf(getResources().getText(R.string.select)));
        languageList.add(String.valueOf(getResources().getText(R.string.select)));
        initlaizefields();

//                        Thread.sleep(200);
                        getcountryparsevalues();
                        getlanguages();


//                        setspinnervalues();









        maritalStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                maritalStatusString=maritalStatusSpinner.getSelectedItem().toString().trim();
                if ((maritalStatusString.equalsIgnoreCase("Widowed")) ||
                        (maritalStatusString.equalsIgnoreCase("Divorced")) ||
                        (maritalStatusString.equalsIgnoreCase("Awaiting divorce")))
                {
                    haveKidsLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "displayconditon met", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    haveKidsLayout.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        havingKidsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String val=havingKidsSpinner.getSelectedItem().toString().trim();

                if(val.equals("Yes"))
                {
                    kidsLessLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    kidsLessLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        kidsCountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String kidscount=kidsCountSpinner.getSelectedItem().toString().trim();
                if (kidscount.equalsIgnoreCase("More than 3"))
                {
                    kidsMoreLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    kidsMoreLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
           livingPlaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    living=livingPlaceSpinner.getSelectedItem().toString().trim();

                   String isd=null;

                   if(!(living.equals("Select")))
                   {
                       stateLayout.setVisibility(View.VISIBLE);


                   for(int i=0;i<countryList.size();i++)
                   {
                       for(int j=0;j<country_ID.size();j++) {
                           if (living .equals(countryList.get(j)))
                           {
                               isd = country_ID.get(j);


                           }
                       }
                       for(int k=0;k<phonecodeList.size();k++)
                       {
                           if(living.equals(countryList.get(k)))
                           {
                               selectedPhoneCode=phonecodeList.get(k-1);
                           }
                       }


                   }

                   tempPhonelist=new ArrayList<String>();
                   tempPhonelist.add(selectedPhoneCode);

                   for(int i=0;i<phonecodeList.size();i++)
                   {

                           tempPhonelist.add(phonecodeList.get(i));


                   }

                   phonecodevalues();


//                       Log.d(TAG, "onItemSelected: phcode"+selectedPhoneCode);
//
                       cid=Integer.parseInt(isd);
                  finid= cid-1;
//                       Log.d(TAG, "onItemSelected: "+finid);


//                       stateFinalUrl=Urls.STATE_LIST+String.valueOf(finid);
                       getstatelist();


               }

               else
                   {
                       stateLayout.setVisibility(View.GONE);
                   }
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

           stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
               {
                   String state=stateSpinner.getSelectedItem().toString().trim();
                   String city_id=null;

                   if(!state.equals("Select"))
                   {
                       cityLayout.setVisibility(View.VISIBLE);
//                       Log.d(TAG, "onItemSelected: "+stateList.size());
//                       Log.d(TAG, "onItemSelected: "+state_ID.size());
                       for(int i=0;i<stateList.size();i++)
                       {          if(state.equals(stateList.get(i)))
                               {
                                   Log.d(TAG, "onItemSelected: "+stateList.get(i));

                                   city_id=state_ID.get(i);
                                   Log.d(TAG, "onItemSelected:stateid bef"+city_id);
                               }

                       }
                        sid=Integer.parseInt(city_id);
                        finalsid=sid-1;
//
                       Log.d(TAG, "onItemSelected: after"+finalsid);
//

                   if(finalsid!=0) {
                       cityFinalUr = Urls.CITY_LIST + String.valueOf(finalsid);
//                       Log.d(TAG, "onItemSelected: "+cityFinalUr);
                       getcityvalues();

                   }

                   }

                   else
                   {
                       cityLayout.setVisibility(View.GONE);
                   }
               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });


    }

    public void  phonecodevalues()
    {
        String[] ph=new String[tempPhonelist.size()];
        for(int i=0;i<tempPhonelist.size();i++)
        {
            ph[i]=tempPhonelist.get(i);
        }

        ArrayAdapter<String>phoneAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                ph);
        phoneAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        phoneCodeSpinner.setAdapter(phoneAdapter);
    }

    public void getcityvalues()
    {

        AndroidNetworking.get(cityFinalUr)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .addQueryParameter("state_id",String.valueOf(finalsid))
                .build()
        .getAsJSONObject(new JSONObjectRequestListener()
        {
            JSONArray jsonArray=null;
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d(TAG, "onResponse:city "+response.toString());
                try {
                    jsonArray=response.getJSONArray("cities");
                    if(jsonArray.length()!=0)
                    {
                        displaycityvalues(jsonArray.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(ANError anError)
            {
                Log.d(TAG, "onError: "+anError.getErrorBody());

            }
        });
    }


    public void initlaizefields() {
        maritalStatusSpinner = (Spinner) findViewById(R.id.regmartialstatus);
        motherToungeSpinner = (Spinner) findViewById(R.id.mothertounge);
        nationalitySpinner = (Spinner) findViewById(R.id.nationalities);
        livingPlaceSpinner = (Spinner) findViewById(R.id.wheredoyoulive);
        residencyStatusSpinner = (Spinner) findViewById(R.id.residencystatus);
        grownupSpinner = (Spinner) findViewById(R.id.wheredoyougrewup);
        phoneCodeSpinner = (Spinner) findViewById(R.id.phonecode);
        languagesSpokenSpinner = (MultiSpinner) findViewById(R.id.languagesspoken);
        kidsCountSpinner=(Spinner)findViewById(R.id.kidscount);
        havingKidsSpinner=(Spinner)findViewById(R.id.kids);
        haveKidsLayout = (LinearLayout)findViewById(R.id.havingkids);
        kidsMoreLayout=(LinearLayout)findViewById(R.id.kidsmorethan3layout);
        kidsLessLayout=(LinearLayout)findViewById(R.id.kidslessthan3layout);
        stateSpinner=(Spinner)findViewById(R.id.stae);
        citySpinner=(Spinner)findViewById(R.id.city);
        stateLayout=(LinearLayout)findViewById(R.id.stateholder);
        cityLayout=(LinearLayout)findViewById(R.id.citielayout);
        phoneCodeSpinner=(Spinner)findViewById(R.id.phonecode);



    }

    public void getstatelist()
    {
        AndroidNetworking.get(Urls.STATE_LIST)
                .setTag("test")
                .addQueryParameter("country_id",String.valueOf(finid))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener()
                {
                    JSONArray stateArray=null;
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, "onResponse: "+response.toString());
                        try {
                            stateArray=response.getJSONArray("states");
                            if(stateArray.length()!=0)
                            {
                                displaystatelist(stateArray.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                        Log.d(TAG, "onError: "+anError.getErrorBody());

                    }
                });
    }






    public void displaystatelist(String s)
    {
        StateParser stateParser=new StateParser(s);
        stateParser.parsejson();
        stateList=new ArrayList<String>();
        stateList.add(String.valueOf(getResources().getText(R.string.select)));

        for(int i=0;i<StateParser.state_name.length;i++)
        {
            stateList.add(StateParser.state_name[i]);
            state_ID.add(StateParser.state_id[i]);

        }




        String[] stateString=new String[stateList.size()];
        for(int i=0;i<stateList.size();i++)
        {
            stateString[i]=stateList.get(i);
        }

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayoutt
                , stateString);
        stateAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        stateSpinner.setAdapter(stateAdapter);


    }

    public void displaycityvalues(String s)
    {

        CityParser cityParser=new CityParser(s);
        cityParser.parsejson();
        cityList=new ArrayList<String>();
//        cityList.add(String.valueOf(getResources().getText(R.string.select)));
        city_ID=new ArrayList<String>();

        for(int i=0;i<CityParser.city_name.length;i++)
        {
            cityList.add(CityParser.city_name[i]);
            city_ID.add(CityParser.city_id[i]);

        }

        String[] cityString=new String[cityList.size()];

        for(int i=0;i<cityList.size();i++)
        {
            cityString[i]=cityList.get(i);
        }

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayoutt
                , cityString);
        cityAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        citySpinner.setAdapter(cityAdapter);

    }
    public void getlanguages() {

        AndroidNetworking.get(Urls.LANGUAGES_LIST)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray languages = null;
//                        Log.d(TAG, "onResponse: languages"+response.toString());

                        try {
                            languages = response.getJSONArray("languages");
                            if (languages.length() != 0) {
                                languagvalues(languages.toString());
                                languageProgress+=1;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }


    public void getcountryparsevalues() {
        AndroidNetworking.get(Urls.COUNTRIES_LIST)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray countryName = null;
                        String countires = response.toString();
                        try {
                            countryName = response.getJSONArray("countries");
                            if (countryName.length() != 0) {
                                countryvalues(countryName.toString());
                                countryProgress+=1;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }


    public void countryvalues(String s) {
        CountryParser countryParser = new CountryParser(s);
        countryParser.parsejson();

//        Log.d(TAG, "countryvalues: 123"+countryString[0].toString());


        for(int i=0;i<CountryParser.counrty_id.length;i++)
        {
            country_ID.add(CountryParser.counrty_id[i]);
        }

        for(int j=0;j<CountryParser.country_phonecode.length;j++)
        {
            phonecodeList.add(CountryParser.country_phonecode[j]);
        }
        for(int i=0;i<CountryParser.country_name.length;i++)
        {
            countryList.add(CountryParser.country_name[i]);

//            Log.d(TAG, "countryvalues: list"+countryList.toString());

//            Log.d(TAG, "countryvalues: "+CountryParser.country_name[i]);
        }
        countryString=new String[countryList.size()];

        for(int i=0;i<countryList.size();i++)
        {
            countryString[i]=countryList.get(i);
        }
        for(int i=0;i<countryString.length;i++)
        {
//            Log.d(TAG, "countryvalues: "+countryString[i]);
        }
        addcountryvalues();


    }

    public void languagvalues(String s) {
        LanguageParser languageParser = new LanguageParser(s);
        languageParser.parsejson();

        for(int i=0;i<LanguageParser.language_name.length;i++)
        {
            languageList.add(LanguageParser.language_name[i]);
        }
        motherToungeString=new String[LanguageParser.language_name.length];

        for(int i=0;i<motherToungeString.length;i++)
        {
            motherToungeString[i]=languageList.get(i);
        }

        for (int i = 0; i < LanguageParser.language_name.length; i++) {
//            Log.d(TAG, "languagvalues: language" + LanguageParser.language_name[i]);
//            Log.d(TAG, "languagvalues: id" + LanguageParser.lanugae_id[i]);
//
        }
        addlanguagevalues();
    }

    public void addlanguagevalues()
    {


        ArrayAdapter<String> motherToungeAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayoutt
                , motherToungeString);
        motherToungeAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        ArrayAdapter<String> languagesSpokenAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayoutt
                , R.id.text, motherToungeString);
        motherToungeSpinner.setAdapter(motherToungeAdapter);

        languagesSpokenSpinner.setAdapter(languagesSpokenAdapter, false, onSelectedListener);
        languagesSpokenSpinner.setTextColor(getResources().getColor(R.color.white));

        boolean[] selectedItems = new boolean[languagesSpokenAdapter.getCount()];
        selectedItems[0] = true;


    }

    public MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        @Override
        public void onItemsSelected(boolean[] selected)
        {

        }
    };


    public void addcountryvalues()
    {

        residentStatus=getResources().getStringArray(R.array.residency_status);
        martialStatus=getResources().getStringArray(R.array.martialStatus);
        kidsCountString=getResources().getStringArray(R.array.no_of_kids);
        havingKids=getResources().getStringArray(R.array.yes_no);



        ArrayAdapter<String>countryAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                countryString);
        countryAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        ArrayAdapter<String>livingPlace=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                countryString);
        livingPlace.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        ArrayAdapter<String>grownUpAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                countryString);
        grownUpAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        ArrayAdapter<String>residenceAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                residentStatus);
        residenceAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        grownUpAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        ArrayAdapter<String>martialADapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                martialStatus);
        martialADapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        ArrayAdapter<String>kidscountAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,kidsCountString);
        kidscountAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        ArrayAdapter<String>havingkidsAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,havingKids);
        havingkidsAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);

        nationalitySpinner.setAdapter(countryAdapter);
        grownupSpinner.setAdapter(livingPlace);
        livingPlaceSpinner.setAdapter(grownUpAdapter);
        maritalStatusSpinner.setAdapter(martialADapter);
        residencyStatusSpinner.setAdapter(residenceAdapter);
        kidsCountSpinner.setAdapter(kidscountAdapter);
        havingKidsSpinner.setAdapter(havingkidsAdapter);



    }
}

