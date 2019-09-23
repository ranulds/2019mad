package com.example.rajadhahana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends AppCompatActivity {

    private static final String TAG = "OrderList";

    Package p;
    DatabaseReference dbref;
    String cusname;
    ListView listView;
    List<Package> packageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        listView = (ListView)findViewById(R.id.thelist);
        packageList = new ArrayList<>();
        cusname ="Lakshan";
        dbref = FirebaseDatabase.getInstance().getReference().child("Package");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                packageList.clear();

                for(DataSnapshot packSnapshot : dataSnapshot.getChildren()){
                    Package pac = packSnapshot.getValue(Package.class);
                    if(pac.getCusName().equals("Lakshan"))
                        packageList.add(pac);
                }
                Olist adapter = new Olist(OrderList.this, packageList);
                listView.setAdapter(adapter);

/*                 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                          delete(i);
                     }
                 });*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /*public void delete( int j){
        final int m = j;
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderList.this);

        builder.setMessage("Are you sure?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Package");
                delRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //    if (dataSnapshot.hasChild(packageList.get(m).getDate())) {
                            dbref = FirebaseDatabase.getInstance().getReference().child("Package").child(packageList.get(m).getDate());
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(), "Record Cancelled successfully", Toast.LENGTH_SHORT).show();
                            //p.setPrice((double) 10);
                           // recreate();
                    //    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        }).setNegativeButton("Cancel",null);

        AlertDialog alert = builder.create();
        alert.show();


    }*/

}

