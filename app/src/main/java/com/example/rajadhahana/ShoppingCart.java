package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingCart extends AppCompatActivity {

    TextView txtret1,txtret2,txtret3,txtret4,txtret5,txtret6,pck,pck1,pck2,pck3,pck4,pck5,pck6;
    ImageButton btnupdate,btndelete;
    View view4;
    Button btnorder,btnorder2,hide;
    String s1;
    Package p;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        txtret1 = findViewById(R.id.txtret1);
        txtret2 = findViewById(R.id.txtret2);
        txtret3 = findViewById(R.id.txtret3);
        txtret4 = findViewById(R.id.txtret4);
        txtret5 = findViewById(R.id.txtret5);
        txtret6 = findViewById(R.id.txtret6);


        pck = findViewById(R.id.textView19);
        pck1 = findViewById(R.id.textView20);
        pck2 = findViewById(R.id.textView18);
        pck3 = findViewById(R.id.textView21);
        pck4 = findViewById(R.id.textView22);
        pck5 = findViewById(R.id.textView23);
        pck6 = findViewById(R.id.textView27);





        view4 = findViewById(R.id.view4);

        p = new Package();
        // p.setDate("laka");
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);
        btnorder = findViewById(R.id.btnorder);
        btnorder2 = findViewById(R.id.btnorder3);
        hide = findViewById(R.id.cnt);
        final String time="time";


        Intent o = getIntent();
        s1 = o.getStringExtra("date");


        final DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Package");

        readRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(s1)) {
                    p = dataSnapshot.child(s1).getValue(Package.class);
                    txtret1.setText(p.getName());
                    txtret2.setText("Rs."+p.getPrice().toString());
                    txtret3.setText(p.getType());
                    txtret4.setText(p.getDate());
                    txtret5.setText(p.getTime());
                    txtret6.setText(p.getCount().toString()+"Guests");
                    System.out.println("=================" + dataSnapshot.child(s1).child("time").getValue());
                    hide.setVisibility(View.INVISIBLE);
                    pck5.setVisibility(View.INVISIBLE);


                    //txtret1.setText(dataSnapshot.child("name").getValue().toString());

                }else {
                    pck.setVisibility(View.INVISIBLE);
                    pck1.setVisibility(View.INVISIBLE);
                    pck2.setVisibility(View.INVISIBLE);
                    pck3.setVisibility(View.INVISIBLE);
                    pck4.setVisibility(View.INVISIBLE);
                    pck6.setVisibility(View.INVISIBLE);


                    btnupdate.setVisibility(View.INVISIBLE);
                    btndelete.setVisibility(View.INVISIBLE);
                    view4.setVisibility(View.INVISIBLE);



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShoppingCart.this,UpdateResevation.class);
                intent.putExtra("date",s1);
                startActivity(intent);
                final String d = p.getDate();

                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Package");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(p.getDate())){
                            try{

                                p.setName(txtret1.getText().toString().trim());
                                p.setPrice(Double.parseDouble(txtret2.getText().toString().trim()));
                                p.setType(txtret3.getText().toString().trim());
                                p.setDate(txtret4.getText().toString().trim());
                                p.setTime(txtret5.getText().toString().trim());
                                p.setCount(Integer.parseInt(txtret6.getText().toString().trim()));

                                dbref = FirebaseDatabase.getInstance().getReference().child("Package");
                                dbref.child(d).setValue(p);

                                Toast.makeText(getApplicationContext(), "Reservation details updated successfully", Toast.LENGTH_SHORT).show();



                            }catch (Exception e){
                                System.out.println("Errooorrr ");
                            }


                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(ShoppingCart.this,Resevation.class);
                startActivity(k);
            }
        });



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);

                builder.setMessage("Do you want Cancel Reservation?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Package");
                        delRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(p.getDate())) {
                                    dbref = FirebaseDatabase.getInstance().getReference().child("Package").child(p.getDate());
                                    dbref.removeValue();
                                    Toast.makeText(getApplicationContext(), "Reservation Cancelled successfully", Toast.LENGTH_SHORT).show();
                                    //p.setPrice((double) 10);
                                    recreate();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }
                }).setNegativeButton("Cancel",null);

                AlertDialog alert = builder.create();
                alert.show();


            }
        });





        //  System.out.println("+++++++SDDAS{{"+p.getDate());

  /*  btnorder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   Intent i = new Intent(ShoppingCart.this, Resevation.class);
                   i.putExtra("price",p.getPrice());
                   startActivity(i);


           }
       });

*/




        btnorder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(ShoppingCart.this,OrderList.class);
                startActivity(k);
            }
        });


    }

    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCart.this);

        builder.setTitle("Do you want to leave Reservation?").setMessage("Are you sure?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
                Intent in =new Intent(ShoppingCart.this,Resevation.class);
                startActivity(in);
            }
        }).setNegativeButton("Cancel",null);

        AlertDialog alert = builder.create();
        alert.show();

    }


    public void delete(){

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Package");
        delRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(p.getDate())) {
                    dbref = FirebaseDatabase.getInstance().getReference().child("Package").child(p.getDate());
                    dbref.removeValue();
                    Toast.makeText(getApplicationContext(), "Reservation Cancelled successfully", Toast.LENGTH_SHORT).show();
                    //p.setPrice((double) 10);
                    recreate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



}


