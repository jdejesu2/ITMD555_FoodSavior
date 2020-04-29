package com.example.foodsavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Recipes extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Food> list;
    RecyclerView recyclerView;
    SearchView searchView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        ref = FirebaseDatabase.getInstance().getReference("foodsavior-ff4d9");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ref != null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //String value = dataSnapshot.getValue(String.class);
                    //Log.d(TAG, "Value is: " + value);

                    if (dataSnapshot.exists())
                    {

                        list = new ArrayList<>();

                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {

                            list.add(ds.getValue(Food.class));

                            Log.d(TAG, "Value is: " + list);
                        }

                        AdapterClass adapterClass = new AdapterClass(list);
                        Log.d(TAG, "Value is: ");
                        Log.d(TAG, "Value is: ");
                        Log.d(TAG, "Value is: ");
                        Log.d(TAG, "Value is: ");
                        recyclerView.setAdapter(adapterClass);
                    }


                }

                @Override
                public void onCancelled(@NotNull DatabaseError databaseError) {
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());
                    Toast.makeText(Recipes.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

    }

    private void search (String str)
    {
        ArrayList<Food> myList = new ArrayList<>();

        for (Food object : list)
        {
            if (object.getName().toLowerCase().contains(str.toLowerCase()))
            {
                myList.add(object);
            }
        }

        AdapterClass adapterClass = new AdapterClass(myList);
        Log.d(TAG, "Value is: ");
        Log.d(TAG, "Value is: ");
        Log.d(TAG, "Value is: ");
        Log.d(TAG, "Value is: ");
        recyclerView.setAdapter(adapterClass);

    }


}
