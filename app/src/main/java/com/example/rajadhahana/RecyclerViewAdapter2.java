package com.example.rajadhahana;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    Context context;
    List<Invitation> InvitaionList;

    public RecyclerViewAdapter2(Context context, List<Invitation> TempList) {

        this.InvitaionList = TempList;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items2, parent, false);

        RecyclerViewAdapter2.ViewHolder viewHolder = new RecyclerViewAdapter2.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter2.ViewHolder holder, final int position) {

        final Invitation invitations = InvitaionList.get(position);

        holder.NameTextView.setText(invitations.getSenderName());

        holder.MessageTextView.setText(invitations.getMessage());

        final String key = invitations.getKey();

        holder.mDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Invitation");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ref.child(invitations.getKey()).removeValue();
                        InvitaionList.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Read.class);
                intent.putExtra("key",key);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return InvitaionList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView NameTextView;
        public TextView MessageTextView;
        public ImageButton mDeleteImage;
        public RelativeLayout rl;

        public ViewHolder(View itemView) {

            super(itemView);

            NameTextView = itemView.findViewById(R.id.NameTextView2);

            MessageTextView = itemView.findViewById(R.id.MessageTextView2);

            mDeleteImage = itemView.findViewById(R.id.image_delete2);

            rl = itemView.findViewById(R.id.rl2);
        }
    }
}
