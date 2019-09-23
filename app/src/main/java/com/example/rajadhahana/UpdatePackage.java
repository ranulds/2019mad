package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdatePackage extends AppCompatActivity {

    TextView txtpack1,txtprice1,txtpack2,txtprice2,menu,menu1;
    Button btnpack1,btnpack2;
    DatabaseReference dbRef;
    Package pk;
    String cusname,kala;

    String vari= "second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_package);

        btnpack1 = findViewById(R.id.btnpack1);
        txtpack1 = findViewById(R.id.txtpack1);
        txtprice1 = findViewById(R.id.txtprice1);

        menu = findViewById(R.id.menu);
        menu1 = findViewById(R.id.menu1);

        btnpack2 = findViewById(R.id.btnpack2);
        txtpack2 = findViewById(R.id.txtpack2);
        txtprice2 = findViewById(R.id.txtprice2);

        pk = new Package();

        cusname = "Lakshan";


        btnpack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // dbRef = FirebaseDatabase.getInstance().getReference().child("Package");

                Intent i = getIntent();
                String s1 = i.getStringExtra("value");
                String s2 = i.getStringExtra("value1");
                String s3 = i.getStringExtra("value2");
                Integer s4 = (Integer) i.getIntExtra("value3",0);
                kala = i.getStringExtra("laka");


                pk.setName(txtpack1.getText().toString().trim());
                pk.setPrice(Double.parseDouble(txtprice1.getText().toString().trim()));

                pk.setDate(s1);
                pk.setTime(s2);
                pk.setType(s3);
                pk.setCount(s4);
                pk.setCusName(cusname);


                final DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Package");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(kala))
                            upref.child(pk.getDate()).setValue(pk);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdatePackage.this,ShoppingCart.class);
                intent.putExtra("date",pk.getDate());
                startActivity(intent);



            }

        });






        btnpack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // dbRef = FirebaseDatabase.getInstance().getReference().child("Package");

                Intent i = getIntent();
                String s1 = i.getStringExtra("value");
                String s2 = i.getStringExtra("value1");
                String s3 = i.getStringExtra("value2");
                kala = i.getStringExtra("laka");
                Integer s4 = (Integer) i.getIntExtra("value3",0);


                pk.setName(txtpack2.getText().toString().trim());
                pk.setPrice(Double.parseDouble(txtprice2.getText().toString().trim()));

                pk.setDate(s1);
                pk.setTime(s2);
                pk.setType(s3);
                pk.setCount(s4);
                pk.setCusName(cusname);


                final DatabaseReference upref = FirebaseDatabase.getInstance().getReference().child("Package");
                upref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(kala))
                            upref.child(pk.getDate()).setValue(pk);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                Toast.makeText(getApplicationContext(),"Data Updated successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdatePackage.this,ShoppingCart.class);

                intent.putExtra("check",vari);
                intent.putExtra("date",pk.getDate());


                startActivity(intent);

            }

        });



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePackage.this);

                builder.setTitle("Menu Items")

                        .setMessage("Fresh Papaya juice"+"\n"
                                +"Fruit punch"+"\n"
                                +"Basmathi F/Rice"+"\n"
                                +"Steamed Rice"+"\n"
                                +"Buttered Noodles"+"\n"
                                +"Mixed Vegetable salad"+"\n"
                                +"Egg salad"+"\n"
                                +"Roast chicken with ginger & garlic Sauce"+"\n"
                                +"dhal curry"+"\n"
                                +"Cashew nut thick curry"+"\n"
                                +"Chicken black curry"+"\n"
                                +"Cream caramal pudding"+"\n"
                                +"Jelly & custard"+"\n"
                                +"Fresh fruits seasonal"+"\n"
                                +"Iced coffee or Blacked coffee"+"\n")


                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePackage.this);

                builder.setTitle("Menu Items")

                        .setMessage("Fresh Papaya juice"+"\n"
                                +"Fruit punch"+"\n"
                                +"Fruit punch"+"\n"
                                +"Basmathi F/Rice"+"\n"
                                +"Steamed White Rice"+"\n"
                                +"Savory yellow rice"+"\n"
                                +"Garlic flavored butter noodles"+"\n"
                                +"Mixed Vegetable salad"+"\n"
                                +"Egg salad"+"\n"
                                +"Roast chicken with ginger & garlic Sauce"+"\n"
                                +"dhal curry"+"\n"
                                +"Cashew nut thick curry"+"\n"
                                +"Chicken black curry"+"\n"
                                +"Fish stew with mustard"+"\n"
                                +"Maldive Fish sambal"+"\n"
                                +"Fish cutlets"+"\n"
                                +"Cream caramal pudding"+"\n"
                                +"Fruit salad"+"\n"
                                +"Jelly & custard"+"\n"
                                +"Fresh fruits seasonal"+"\n"
                                +"Iced coffee or Blacked coffee"+"\n"

                        )


                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }





}
