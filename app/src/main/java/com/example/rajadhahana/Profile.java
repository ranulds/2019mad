package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    EditText txtContactNo,txtNic,txtEmail,txtPassword;
    TextView txtName;
    User us;
    Button update,delete;
    DatabaseReference dbref;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtName = findViewById(R.id.editText10);
        txtContactNo = findViewById(R.id.editText13);
        txtNic = findViewById(R.id.editText14);
        txtEmail = findViewById(R.id.editText15);
        // txtPassword = findViewById(R.id.editText16);
        update = findViewById(R.id.button);
        delete = findViewById(R.id.button5);
        Intent intent2 = getIntent();
        n = intent2.getStringExtra("name");
        us = new User();

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("User").child(n);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtContactNo.setText(dataSnapshot.child("contactNo").getValue().toString());
                    txtNic.setText(dataSnapshot.child("nic").getValue().toString());
                    txtEmail.setText(dataSnapshot.child("email").getValue().toString());
                    // txtPassword.setText(dataSnapshot.child("password").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "No sourse to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("User");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(n)) {
                            us = dataSnapshot.child(n).getValue(User.class);
                            String email=txtEmail.getText().toString().trim();
                            try {
                                if (TextUtils.isEmpty(txtEmail.getText().toString()))
                                    Toast.makeText(getApplicationContext(), "enter email", Toast.LENGTH_SHORT).show();
                                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                                    txtEmail.setError("Invalid Email");
                                    txtEmail.setFocusable(true);
                                }


                                else{// us.setName(txtName.getText().toString().trim());
                                    us.setContactNo(Integer.parseInt(txtContactNo.getText().toString().trim()));
                                    us.setNic(txtNic.getText().toString().trim());
                                    us.setEmail(txtEmail.getText().toString());
                                    //         us.setPassword(txtPassword.getText().toString());

                                    dbref = FirebaseDatabase.getInstance().getReference().child("User").child(n);
                                    dbref.setValue(us);
                                    Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No sourse to update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    public void Delete(View view){
        DatabaseReference delRef=FirebaseDatabase.getInstance().getReference().child("User");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(n)){
                    dbref =FirebaseDatabase.getInstance().getReference().child("User").child(n);
                    dbref.removeValue();
                    Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                    Intent intent10=new Intent(Profile.this,login.class);
                    startActivity(intent10);
                }
                else
                    Toast.makeText(getApplicationContext(),"No sourse deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void changepw(View view){
        Intent intent5=new Intent(this,newre.class);
        intent5.putExtra("name",n);
        startActivity(intent5);
    }

}
