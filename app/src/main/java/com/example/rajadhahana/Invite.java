package com.example.rajadhahana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;

public class Invite extends AppCompatActivity {

    String[] suggestions = {"Come to Sanuka Juice Bar","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    Button SubmitButton;

    Button DisplayButton, ListButton;

    EditText NameEditText, PhoneNumberEditText, MessageEditText;

    String NameHolder, NumberHolder, MessageHolder;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

//        final String number = "770540034";

        MultiAutoCompleteTextView simpleMultiAutoCompleteTextView = findViewById(R.id.invite);
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions);
        simpleMultiAutoCompleteTextView.setAdapter(versionNames);

        simpleMultiAutoCompleteTextView.setThreshold(1);
        simpleMultiAutoCompleteTextView.setTokenizer(new SpaceTokenizer());

        SubmitButton = findViewById(R.id.submit);

        NameEditText = findViewById(R.id.name);

        PhoneNumberEditText = findViewById(R.id.number);

        MessageEditText = findViewById(R.id.invite);

        DisplayButton = findViewById(R.id.DisplayButton);

        ListButton = findViewById(R.id.buttonList);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Invitation");

                if(TextUtils.isEmpty(NameEditText.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                else if(!isValid(PhoneNumberEditText.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Enter Valid Phone Number: 7xxxxxxxx",Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(MessageEditText.getText().toString()))
                    Toast.makeText(getApplicationContext(),"Enter Message",Toast.LENGTH_SHORT).show();
                else {
                    Invitation invite = new Invitation();
                    GetDataFromEditText();
                    invite.setName(NameHolder);
                    invite.setNumber(Integer.parseInt(NumberHolder));
                    invite.setMessage(MessageHolder);
                    invite.setSenderNumber(Invitation.getMynumber());
                    invite.setSenderName(Invitation.getMyName());
                    databaseReference.push().setValue(invite);
                    clearControls();
                    Toast.makeText(Invite.this, "Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();
                }
            }
        });

        DisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Invite.this, ShowInvitation.class);
                startActivity(intent);

            }
        });

        ListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Invite.this,Invited.class);
                startActivity(intent);
            }
        });

    }

    public void GetDataFromEditText() {

        NameHolder = NameEditText.getText().toString().trim();

        MessageHolder = MessageEditText.getText().toString().trim();

        NumberHolder = PhoneNumberEditText.getText().toString().trim();

    }

    public void clearControls(){
        NameEditText.setText("");
        PhoneNumberEditText.setText("");
    }

    public boolean isValid(String number){
        Pattern p = Pattern.compile("(7)?[0-9]{8}");
        Matcher m = p.matcher(number);
        return (m.find() && m.group().equals(number));
    }
}
