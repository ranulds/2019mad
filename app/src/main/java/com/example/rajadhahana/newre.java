package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class newre extends AppCompatActivity {

    EditText txtp1,txtp2,txtp3;
    Button b2;
    String n;
    DatabaseReference dbref;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newre);txtp1=findViewById(R.id.editText4);

        txtp2=findViewById(R.id.editText6);
        txtp3=findViewById(R.id.editText7);
        b2=findViewById(R.id.button4);

        Intent i = getIntent();
        n =i.getStringExtra("name");
        // b2.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View view) {
        // Intent ip=new Intent(newre.this,Dashboard.class);
        //startActivity(ip);
        // }
        // });

    }

    public void check(View view){
        final String pw1 = txtp1.getText().toString().trim();
        final String pw2 = txtp2.getText().toString().trim();
        final String pw3 = txtp3.getText().toString().trim();

        DatabaseReference  upref = FirebaseDatabase.getInstance().getReference().child("User");
        upref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(n)) {
                    u = dataSnapshot.child(n).getValue(User.class);
                    if (pw1.equals(dataSnapshot.child(n).child("password").getValue().toString())){
                        DatabaseReference  upref1 = FirebaseDatabase.getInstance().getReference().child("User");
                        upref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(n)){

                                    if(!pw2.equals(null) && !pw3.equals(null)){
                                        if(pw2.length()>6 && pw3.length()>6){
                                            if(pw2.equals(pw3)){
                                                u.setPassword(pw2);
                                                u.setCopassword(pw3);
                                            }else{
                                                Toast.makeText(getApplicationContext(), "not equal", Toast.LENGTH_SHORT).show();

                                            }

                                        }else{
                                            Toast.makeText(getApplicationContext(), "less than", Toast.LENGTH_SHORT).show();

                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(), "enter valid password", Toast.LENGTH_SHORT).show();
                                    }


                                    dbref = FirebaseDatabase.getInstance().getReference().child("User").child(n);
                                    dbref.setValue(u);
                                    Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }else
                        Toast.makeText(getApplicationContext(),"First worng",Toast.LENGTH_SHORT).show();
                }
                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ip=new Intent(newre.this,Dashboard.class);
                        startActivity(ip);
                    }
                });

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
