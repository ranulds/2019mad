package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateInvite extends AppCompatActivity {

    String key;
    TextView name, number;
    EditText message;
    Button update;
    String[] suggestions = {"Come to Sanuka Juice Bar","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_invite);

        name = findViewById(R.id.updateName);
        number = findViewById(R.id.updateNumber);
        message = findViewById(R.id.updateMessage);

        MultiAutoCompleteTextView simpleMultiAutoCompleteTextView = findViewById(R.id.updateMessage);
        ArrayAdapter<String> versionNames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions);
        simpleMultiAutoCompleteTextView.setAdapter(versionNames);

        simpleMultiAutoCompleteTextView.setThreshold(1);
        simpleMultiAutoCompleteTextView.setTokenizer(new SpaceTokenizer());

        final Intent intent = getIntent();
        key = intent.getStringExtra("key");

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Invitation").child(key);
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("name").getValue().toString());
                number.setText(dataSnapshot.child("number").getValue().toString());
                message.setText(dataSnapshot.child("message").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Invitation").child(key);
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Invitation invitation = new Invitation();
                        invitation.setName(name.getText().toString().trim());
                        invitation.setNumber(Integer.parseInt(number.getText().toString().trim()));
                        invitation.setMessage(message.getText().toString().trim());
                        invitation.setSenderName(Invitation.getMyName());
                        invitation.setSenderNumber(Invitation.getMynumber());

                        dbref.setValue(invitation);

                        Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}

