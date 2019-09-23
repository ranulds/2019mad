package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UpdateResevation extends AppCompatActivity {

    private static final String TAG = "UpdateResevation";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    TextView txtres1;
    EditText txtres2,chooseTime,txtres3;
    Button btnsave;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    RadioGroup radioGroup1;
    RadioButton radio1,radio2,radio3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resevation);



        mDisplayDate = findViewById(R.id.txtres1);

        mDisplayDate.requestFocus();
        txtres2 = findViewById(R.id.txtres2);
        txtres3 = findViewById(R.id.txtres3);
        btnsave = findViewById(R.id.btnsave);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);

        radioGroup1 = findViewById(R.id.radioGroup1);

        Intent o = getIntent();
        final    String kala = o.getStringExtra("date");

        DatabaseReference rr = FirebaseDatabase.getInstance().getReference().child("Package");
        rr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(kala)){
                    mDisplayDate.setText(dataSnapshot.child(kala).child("date").getValue().toString());
                    txtres2.setText(dataSnapshot.child(kala).child("time").getValue().toString());
                    txtres3.setText(dataSnapshot.child(kala).child("count").getValue().toString());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        chooseTime = findViewById(R.id.txtres2);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(UpdateResevation.this, new TimePickerDialog.OnTimeSetListener() {
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

                if (txtres2.length()==0){
                    Toast.makeText(getApplicationContext(), "Please select Time", Toast.LENGTH_SHORT).show();
                }
                else if (radioGroup1.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select type of your function", Toast.LENGTH_SHORT).show();
                }
                else if (txtres3.length()==0){
                    Toast.makeText(getApplicationContext(), "Please enter count", Toast.LENGTH_SHORT).show();
                }

                else {

                    String s1 = mDisplayDate.getText().toString();
                    String s2 = txtres2.getText().toString();


                    String s3 = ((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).getText().toString();

                    Integer s4 = Integer.parseInt(txtres3.getText().toString());





                    Intent intent = new Intent(UpdateResevation.this, UpdatePackage.class);
                    intent.putExtra("value", s1);
                    intent.putExtra("value1", s2);
                    intent.putExtra("value2", s3);
                    intent.putExtra("laka", kala);
                    intent.putExtra("value3",s4);


                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();


                }
            }



        });
    }
}
