package com.example.rajadhahana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Resevation extends AppCompatActivity {

    private static final String TAG = "Resevation";

    private TextView mDisplayDate;
    private String CurrentDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText txtres1,txtres2,chooseTime,txtres3;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;


    Button btnsave;

    RadioGroup radioGroup1;
    RadioButton radio1,radio2,radio3;
    //DatabaseReference dbRef;
    //Package pk;
    Date d1=null;
    Date d2=null;

    SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resevation);

        mDisplayDate = findViewById(R.id.txtres1);
        chooseTime = findViewById(R.id.txtres2);

        mDisplayDate.requestFocus();
        txtres2 = findViewById(R.id.txtres2);
        txtres3 = findViewById(R.id.txtres3);

        btnsave = findViewById(R.id.btnsave);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);

        radioGroup1 = findViewById(R.id.radioGroup1);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Resevation.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd-mm/yyy: " + day + "/" + month + "/" + year);
                String date = day + "-" + month + "-" + year;
                mDisplayDate.setText(date);
                chooseTime.requestFocus();
            }
        };


        //txtres1 = findViewById(R.id.txtres1);

        //pk = new Package();
        format = new SimpleDateFormat("dd-mm-yyyy");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        CurrentDate = df.format(c);

        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Resevation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12){
                            amPm = "PM";
                        }
                        else{
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                },currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDisplayDate.length()==0){
                    Toast.makeText(getApplicationContext(), "Please select Date", Toast.LENGTH_SHORT).show();
                }
                else if (txtres2.length()==0){
                    Toast.makeText(getApplicationContext(), "Please select Time", Toast.LENGTH_SHORT).show();
                }
                else if (txtres3.length()==0){
                    Toast.makeText(getApplicationContext(), "Please enter count", Toast.LENGTH_SHORT).show();
                }
                else if (radioGroup1.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select type of your function", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s1  = mDisplayDate.getText().toString();
                    String s2 = txtres2.getText().toString();
                    String s3 = ((RadioButton )findViewById(radioGroup1.getCheckedRadioButtonId())).getText().toString();
                    Integer s4 = Integer.parseInt(txtres3.getText().toString());

                    try {
                        d1= format.parse(CurrentDate);
                        d2=format.parse(s1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(d1.after(d2))
                        Toast.makeText(getApplicationContext(),"Enter Valid Date",Toast.LENGTH_LONG).show();

                    else {
                        Intent intent = new Intent(Resevation.this, DisplayPackage.class);
                        intent.putExtra("value", s1);
                        intent.putExtra("value1", s2);
                        intent.putExtra("value2", s3);
                        intent.putExtra("value3",s4);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

