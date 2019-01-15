package com.example.hp.allyseek.registration;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.allyseek.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registrationp2 extends AppCompatActivity
{
    EditText firstName_ET,lastName_ET,otherReligion_ET;
    EditText dateOfBirth;
    Spinner religionSpinner;
    LinearLayout otherReligionHolder,ageHolderLayout;
    CardView signUp;
    Date c ;
    SimpleDateFormat df ;

    String firstName_ST,lastName_ST,religion_ST,maritalStatus_ST;
    public DatePickerDialog datePickerDialog;
    public Calendar calendar;
    public int year, month, day;
    private static final String TAG = "Registrationp2";
    public String currentDate;
    public String dateOfBirthString=null;
    Date currentDate1,dateOfBirth1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = Calendar.getInstance().getTime();
        df = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = df.format(c);

        setContentView(R.layout.activity_registrationp2);
        initializesignup();
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                datePickerDialog=new DatePickerDialog(Registrationp2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int syear, int smonth, int sdayOfMonth)
                    {
                        year=syear;
                        month=smonth;
                        day=sdayOfMonth;
                        dateOfBirth.setText(new StringBuilder().append(day)
                        .append("/").append(month+1).append("/").append(year));
                        updatedate();



                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        String dob=dateOfBirth.getText().toString().trim();
//        Log.d(TAG, "onCreate: "+dob);
//        getdates(currentDate,dateOfBirthString);


//        getDatedifference();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupAndContinue();
            }
        });
    }
public void updatedate()
{
    String date=dateOfBirth.getText().toString();
    Date bDate;
    Calendar birth= null;
    try {
        bDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        birth=Calendar.getInstance();
        birth.setTime(bDate);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Calendar current=Calendar.getInstance();
    int yearDifference=current.get(Calendar.YEAR)-birth.get(Calendar.YEAR);
    Log.d(TAG, "updatedate: "+yearDifference);
//    Log.d(TAG, "updatedate: "+date);
}
    public void getdates(String s1,String s2)
    {
        Log.d(TAG, "onCreate: dob"+dateOfBirthString);

//        try {
//            currentDate1=new SimpleDateFormat("dd/MM/yyy").parse(s1);
//            Log.d(TAG, "getdates: 123"+currentDate1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//                dateOfBirth1 = new SimpleDateFormat("dd/MM/yyy").parse(s2);
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Log.d(TAG, "getdates:current  "+currentDate1);
//        Log.d(TAG, "getdates: 456 "+dateOfBirth1);
    }
    public void initializesignup()
    {
        firstName_ET=(EditText)findViewById(R.id.regfirstname);
        lastName_ET=(EditText)findViewById(R.id.reglastname);
        dateOfBirth=(EditText) findViewById(R.id.regdateofbirth);
        religionSpinner =(Spinner)findViewById(R.id.regreligion);
//        maritalStatusSpinner =(Spinner)findViewById(R.id.regmartialstatus);
        signUp=(CardView)findViewById(R.id.regsignupandcontinue);
        otherReligion_ET=(EditText)findViewById(R.id.otherreligion);
        otherReligionHolder=(LinearLayout)findViewById(R.id.otherreligionholder);

        String[] religionString=getResources().getStringArray(R.array.relegion);
        ArrayAdapter<String> religionAdapter=new ArrayAdapter<String>(this,R.layout.spinnerlayoutt,
                R.id.text,religionString);
        religionAdapter.setDropDownViewResource(R.layout.spinnerlayoutt);
        religionSpinner.setAdapter(religionAdapter);


        religionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                religion_ST=religionSpinner.getItemAtPosition(position).toString().trim();

                if(religion_ST.equals(getText(R.string.others)))
                {
                    otherReligionHolder.setVisibility(View.VISIBLE);

                    religion_ST=otherReligion_ET.getText().toString().trim();
                    Log.d(TAG, "initializesignup: "+religion_ST);
                }

                else
                {
                    otherReligionHolder.setVisibility(View.GONE);
                    religion_ST=religionSpinner.getSelectedItem().toString();



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }

    public  void signupAndContinue()
    {

        Log.d(TAG, "signupAndContinue: "+religion_ST);
        firstName_ST=firstName_ET.getText().toString().trim();
                lastName_ST=lastName_ET.getText().toString().trim();

                if(firstName_ST.isEmpty())
                {
                    firstName_ET.setError("First Name Required");
                }

                if(lastName_ST.isEmpty())

                {
                    lastName_ET.setError("Last Name Required");
                }

                if (religion_ST.equals("Relegion"))
                {
                    Toast.makeText(getApplicationContext(),"Religion Required",Toast.LENGTH_SHORT).show();
                }

//                if(maritalStatusSpinner.getSelectedItem().toString().equals("Marital Status"))
//                {
//                    Toast.makeText(getApplicationContext(),"Martial Status Required",Toast.LENGTH_SHORT).show();
//                }

                if (dateOfBirth.getText().toString().equals("Date Of Birth"))

                {
                    dateOfBirth.setError(getText(R.string.dateofbirtherror));

                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Page 2 Success",Toast.LENGTH_SHORT).show();
                }

    }
}
