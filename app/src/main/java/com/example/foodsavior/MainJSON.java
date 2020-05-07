package com.example.foodsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainJSON extends AppCompatActivity {

    private ListView recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        //List view
        recipes = findViewById(R.id.listView);


        recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //setting an item click listener and every time an item is clicked we
            //create an Intent!
            //we pass the position variable to the intent, this is used to represent the
            //position in the data source for the item clicked.
            public void onItemClick(AdapterView arg0, View arg1, int position,
                                    long arg3) {
                Intent i = new Intent(MainJSON.this, RecipeDetail.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });

        new AsynDataClass().execute();

    }

    private class AsynDataClass extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String...params ) {

            StringBuilder jsonResult = new StringBuilder();

            try {

                URL url = new
                        URL("https://extendsclass.com/api/json-storage/bin/fcfbebb");
                urlConnection = (HttpURLConnection)
                        url.openConnection();
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());

                //reading in and putting into an append JSON
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(in));
                String line;
                //getting all the information
                while ((line = reader.readLine()) != null) {
                    jsonResult.append(line);
                }
                System.out.println("Returned Json url object " +
                        jsonResult.toString());

            } catch (Exception e) {System.out.println("Err: " + e);}
            finally {
                urlConnection.disconnect();
            }
            return jsonResult.toString();
        }

        @Override
        protected void onPreExecute() {  }
        @Override
        protected void onPostExecute(String result) {

            System.out.println("Result on post execute: " + result);
            //parsing the data from the server
            //taking the result and setting them ItemObject
            List<ItemObject> parsedObject = returnParsedJsonObject(result);
            CustomAdapter jsonCustomAdapter = new CustomAdapter(MainJSON.this, parsedObject);
            recipes.setAdapter(jsonCustomAdapter);
        }

    }
    private List<ItemObject> returnParsedJsonObject(String result){

        List<ItemObject> jsonObject = new ArrayList<ItemObject>();
        JSONObject resultObject = null;
        JSONArray jsonArray = null;
        ItemObject newItemObject = null; //interior object holder

        try {
            resultObject = new JSONObject(result);
            System.out.println("Preparsed JSON object " +
                    resultObject.toString());
            // set up json Array to be parsed
            jsonArray = resultObject.optJSONArray("Recipes");
        }
        catch (JSONException e) { e.printStackTrace();
        }

        for(int i = 0; i < jsonArray.length(); i++)

        {
            JSONObject jsonChildNode = null;
            try {
                jsonChildNode = jsonArray.getJSONObject(i);
                //get all data from stream
                String instructions = jsonChildNode.getString("instructions");
                String name = jsonChildNode.getString("name");

                newItemObject = new ItemObject(name, instructions);

                jsonObject.add(newItemObject);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        return jsonObject;
    }
}

