package com.example.rajadhahana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowInvitation extends AppCompatActivity {

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    List<Invitation> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    SearchView searchView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_invitation);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowInvitation.this));
        progressDialog = new ProgressDialog(ShowInvitation.this);
        progressDialog.setMessage("Loading Data from Firebase Database");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Invitation");
        Query query = databaseReference.orderByChild("senderNumber").equalTo(Invitation.getMynumber());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Invitation invitations = dataSnapshot.getValue(Invitation.class);
                    invitations.setKey(dataSnapshot.getKey());
                    System.out.println(dataSnapshot.getKey());
                    list.add(invitations);
                }

                adapter = new RecyclerViewAdapter(ShowInvitation.this, list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });

        searchView = findViewById(R.id.searchView);
        title = findViewById(R.id.showTitle);
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    title.setVisibility(View.INVISIBLE);
                }
            });
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    title.setVisibility(View.VISIBLE);
                    return false;
                }
            });
        }
    }

    private void search(String s){
        ArrayList<Invitation> myList = new ArrayList<>();
        for(Invitation object:list){
            if(object.getName().toLowerCase().contains(s.toLowerCase())){
                myList.add(object);
            }
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(ShowInvitation.this,myList);
            recyclerView.setAdapter(adapter);
        }
    }
}

