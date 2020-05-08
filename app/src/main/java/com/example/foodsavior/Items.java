package com.example.foodsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodsavior.db.ItemHelper;

import java.util.ArrayList;

public class Items extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ItemHelper mHelper;
    private ListView mItemListView;
    private ArrayAdapter<String> mAdapter;

    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mHelper = new ItemHelper(this);
        mItemListView = findViewById(R.id.list_food);

        btnViewAll = findViewById(R.id.button);

        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mHelper.getAllData();
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) { //cycle thru result set
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Details :" + res.getString(2) + "\n\n");

                        }
                        // Show all data

                        showMessage("Virtual Fridge List", buffer.toString());

                        Log.i("Data", buffer.toString());
                        Log.i("Data", "All Data records");
                    }
                }
        );

        updateUI();
        //viewAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.action_add_item:

                LayoutInflater factory = LayoutInflater.from(this);

                final View textEntryView = factory.inflate(R.layout.item_list, null);

                final Button task_delete = textEntryView.findViewById(R.id.task_delete);
                task_delete.setVisibility(View.INVISIBLE);

                final EditText itemEditText = textEntryView.findViewById(R.id.item_title);
                final EditText itemEditAmount = textEntryView.findViewById(R.id.item_amount);

                itemEditAmount.setVisibility(View.VISIBLE);
                itemEditText.setEnabled(true);
                itemEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                itemEditText.setText("", TextView.BufferType.EDITABLE);
                itemEditAmount.setText("", TextView.BufferType.EDITABLE);

                //final EditText itemEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New Fridge Item with Amount")
                        .setMessage("Add a new food item")
                        .setView(textEntryView)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogue, int which) {
                                String item = String.valueOf(itemEditText.getText());
                                String amount = String.valueOf(itemEditAmount.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ItemHelper.ItemFridge.COL_ITEM_TITLE, item);
                                values.put(ItemHelper.ItemFridge.COL_ITEM_AMOUNT, amount);
                                db.insertWithOnConflict(ItemHelper.ItemFridge.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteItem(View view) {

        final View parent = (View) view.getParent();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Are you sure you already ate this item?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogue, int which) {
                        TextView itemTextView = parent.findViewById(R.id.item_title);
                        String item = String.valueOf(itemTextView.getText());
                        SQLiteDatabase db = mHelper.getWritableDatabase();
                        db.delete(ItemHelper.ItemFridge.TABLE, ItemHelper.ItemFridge.COL_ITEM_TITLE + " = ?", new String[]{item});
                        db.close();
                        updateUI();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }




    private void updateUI() {
        ArrayList<String> itemList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ItemHelper.ItemFridge.TABLE,
                new String[] {ItemHelper.ItemFridge.COL_ITEM_TITLE}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(ItemHelper.ItemFridge.COL_ITEM_TITLE);
            itemList.add(cursor.getString(index));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.item_list, R.id.item_title, itemList);

            mItemListView.setAdapter(mAdapter);

        } else {

            mAdapter.clear();
            mAdapter.addAll(itemList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }


    public void viewAll (View view) {

        //setContentView(R.layout.activity_items);

        btnViewAll = findViewById(R.id.button);

        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mHelper.getAllData();
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) { //cycle thru result set
                            buffer.append("Item :" + res.getString(0) + "\n");
                            buffer.append("Amount :" + res.getString(1) + "\n\n");
                        }
                        // Show all data

                        showMessage("Your Virtual Fridge", buffer.toString());

                        Log.i("Data", buffer.toString());
                        Log.i("Data", "All Data records");
                    }
                }
        );
    } //end onCreate()


    public void showMessage (String title, String Message){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }


}