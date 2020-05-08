package com.example.foodsavior;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends BaseAdapter {


    //functionality to inflate list items at runtime as well as declaring a
    //list object of the generic type ItemObject
    private LayoutInflater lInflater;
    private List<ItemObject> listStorage;

    public CustomAdapter(Context context, List<ItemObject> customizedListView) {
        lInflater =(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }


    //allows data to be presented
    @Override
    public int getCount() {
        return listStorage.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView name = null;
        TextView instructions = null;
        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.list, parent,
                    false);
        }


        name = convertView.findViewById(R.id.textView);
        instructions = convertView.findViewById(R.id.textView2);
        name.setText("Name: " + listStorage.get(position).getName());
        instructions.setText("Instructions: " + listStorage.get(position).getInstructions());
        return convertView;


    }


}
