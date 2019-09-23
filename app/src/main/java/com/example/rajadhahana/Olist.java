package com.example.rajadhahana;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Olist extends ArrayAdapter<Package> {

    private Activity context;
    private List<Package> orderlist;

    public Olist(Activity context, List<Package> orderlist){
        super(context, R.layout.orders,orderlist);
        this.context=context;
        this.orderlist=orderlist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.orders,null,true);

        TextView pack = (TextView) listViewItem.findViewById(R.id.pck);
        TextView price = (TextView)listViewItem.findViewById(R.id.prc);
        TextView Type = (TextView)listViewItem.findViewById(R.id.tp);
        TextView Date = (TextView)listViewItem.findViewById(R.id.dt);
        TextView Time = (TextView)listViewItem.findViewById(R.id.tm);
        TextView Count = (TextView)listViewItem.findViewById(R.id.count);
        Package p = orderlist.get(position);

        pack.setText(p.getName());
        price.setText("Rs."+p.getPrice().toString());
        Type.setText("Package Type: "+p.getType());
        Date.setText("Date: "+p.getDate());
        Time.setText("Time: "+p.getTime());
        Count.setText("Count: "+p.getCount().toString());

        return listViewItem;
    }
}

