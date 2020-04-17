package com.example.foodsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mHelper = new ItemHelper(this);
        mItemListView = findViewById(R.id.list_food);

        updateUI();
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

                final EditText itemEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("New Fridge Item with Amount")
                        .setMessage("Add a new food item")
                        .setView(itemEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogue, int which) {
                                String item = String.valueOf(itemEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(ItemHelper.ItemFridge.COL_ITEM_TITLE, item);
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

}