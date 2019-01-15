package com.example.hp.allyseek.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.hp.allyseek.R;
import com.example.hp.allyseek.Urlkeys;
import com.example.hp.allyseek.Urls;
import com.example.hp.allyseek.registration.Registrationp1;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity
{
    EditText userEmail_ET, userPassword_ET;
    CardView loginCard;
    String userEmailString,userPasswordString;
    private static final String TAG = "LoginActivity";
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;
    ConnectivityManager cm;
    NetworkInfo[] netInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo=cm.getAllNetworkInfo();
        userEmail_ET =(EditText)findViewById(R.id.userloginemail_ET);
        userPassword_ET =(EditText)findViewById(R.id.userloginpwd_ET);
        loginCard=(CardView)findViewById(R.id.login_bt);

        loginCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmailString= userEmail_ET.getText().toString().trim();
                userPasswordString= userPassword_ET.getText().toString().trim();

                if(userEmailString.isEmpty())
                {
                    userEmail_ET.setError(getText(R.string.emailempty));
                }


                else if(userPasswordString.isEmpty())
                {
                    userPassword_ET.setError(getText(R.string.passwordempty));
                }

                else

                {

                    parse();
                }


            }
        });
    }


    public void parse()
    {
        AndroidNetworking.post(Urls.LOGIN_URL)
                .setTag("test")
                .addBodyParameter(Urlkeys.LOGIN_USER_EMAIL,userEmailString)
                .addBodyParameter(Urlkeys.LOGIN_USER_PASSWORD,userPasswordString)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                        String jsonStatus = null;
                        String jsnonMessage=null;

                        try {
                            jsonStatus=response.getString("status");


                            if(jsonStatus.equalsIgnoreCase("success"))
                            {


                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this,Registrationp1.class);
                                startActivity(intent);
                            }


                           else if(jsonStatus.equalsIgnoreCase("error"))
                            {
                                jsnonMessage=response.getString("message");
                                Log.d(TAG, "onResponse: "+jsnonMessage);

                                if(jsnonMessage.equalsIgnoreCase("Invalid Username/Email"))
                                {
                                    userEmail_ET.setError(getText(R.string.invalid_user));
                                }

                                else if(jsnonMessage.equalsIgnoreCase("Invalid password"))
                                {
                                    userPassword_ET.setError(getText(R.string.invalid_password));
                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError)
                    {
                        Log.d(TAG, "onError: "+anError.toString());

                    }
                });

    }
}
