package com.example.hp.allyseek.registration;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.allyseek.R;
import com.example.hp.allyseek.Urls;
import com.example.hp.allyseek.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrationp1 extends AppCompatActivity
{
    TextView termsOfUse,login,forgotpassword;
    Spinner profileForSpinner, genderSpinner;
    EditText email_ET,userName_ET,password_ET,confirmPassword_ET;
    CheckBox termsOfUseCheck;
    CardView nextButton;
    String userName_ST,userEmail_ST,password_ST,confirmPassword_ST;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Matcher matcher;
    Pattern pattern;
    SpannableString ss;
    String profileForString,genderString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationp1);

        intializeFields();



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadnextpage();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registrationp1.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        ss=new SpannableString(getText(R.string.termsofuse));
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick( View widget)
            {
                Intent browserintent=new Intent(Intent.ACTION_VIEW,Uri.parse(Urls.TERMS_OF_USE));
                startActivity(browserintent);

            }

            @Override
            public void updateDrawState( TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.RED);
            }
        };

        ss.setSpan(clickableSpan,21,34,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        termsOfUse.setText(ss);
        termsOfUse.setMovementMethod(LinkMovementMethod.getInstance());
        


    }

    public void intializeFields()
    {
        email_ET=(EditText)findViewById(R.id.reguseremail);
        userName_ET=(EditText)findViewById(R.id.regusername);
        password_ET=(EditText)findViewById(R.id.reguserpassword);
        confirmPassword_ET=(EditText)findViewById(R.id.reguserconfirmpassword);
        profileForSpinner =(Spinner)findViewById(R.id.regprofilefor);
        genderSpinner =(Spinner)findViewById(R.id.regusergender);
        termsOfUseCheck=(CheckBox)findViewById(R.id.checkbox_termsofuse);
        nextButton=(CardView)findViewById(R.id.regnextpage);
        termsOfUse=(TextView)findViewById(R.id.termsofusetext);
//        termsOfUse.setText(R.string.termsofuse);
        login=(TextView)findViewById(R.id.regpagelogintext);
        forgotpassword=(TextView)findViewById(R.id.regpageforgotpassword);


        String[]profileForList = getResources().getStringArray(R.array.profile_array);
        ArrayAdapter<String>profileAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                           profileForList);
        profileAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        profileForSpinner.setAdapter(profileAdapter);

        String[] genderList=getResources().getStringArray(R.array.gender_array);
        ArrayAdapter<String>genderAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
               genderList);
        genderAdapter.setDropDownViewResource(R.layout.spinner_custom_dropdown);
        genderSpinner.setAdapter(genderAdapter);



    }


    public  void  loadnextpage()
    {
        userName_ST=userName_ET.getText().toString().trim();
        userEmail_ST=email_ET.getText().toString().trim();
        password_ST=password_ET.getText().toString().trim();
        confirmPassword_ST=confirmPassword_ET.getText().toString().trim();
        profileForString=profileForSpinner.getSelectedItem().toString();
        genderString=genderSpinner.getSelectedItem().toString();

        if(userEmail_ST.isEmpty())
        {
            email_ET.setError(getText(R.string.emailempty));
        }

         else if(isvalidemail()==true)
        {
            email_ET.setError(getText(R.string.invalid_email));
        }
       else if(userName_ST.isEmpty())
        {
            userName_ET.setError(getText(R.string.usernameempty));
        }

       else if(password_ST.isEmpty())
        {
            password_ET.setError(getText(R.string.passwordempty));
        }
      else  if(password_ST.length()<6)
        {
            password_ET.setError(getText(R.string.passwordlengtherror));
        }

     else  if(!(password_ST.equals(confirmPassword_ST)))
        {
            confirmPassword_ET.setError(getText(R.string.passwordmismatcherror));
       }
     else  if(!termsOfUseCheck.isChecked())
        {
            Toast.makeText(getApplicationContext(),getText(R.string.termsofuseerror),Toast.LENGTH_SHORT).show();
        }
     else  if (profileForString.equals("Profile For"))
        {
            Toast.makeText(getApplicationContext(),getText(R.string.profileselection),Toast.LENGTH_SHORT).show();
        }

      else if(genderString.equals("Gender"))
        {
            Toast.makeText(getApplicationContext(),getText(R.string.geendererror),Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(getApplicationContext(),"page1 success",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(),Registrationp2.class);
            startActivity(intent);
        }

    }


public boolean isvalidemail()
{
    pattern=Pattern.compile(emailPattern);
    matcher=pattern.matcher(userEmail_ST);
    if(!matcher.matches()) {
        email_ET.setError(getText(R.string.invalid_email));
        return true;
    }

    else
    return false;
}
}
