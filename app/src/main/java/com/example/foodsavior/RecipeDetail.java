package com.example.foodsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RecipeDetail extends AppCompatActivity {

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        Intent i = getIntent();

        mPosition = i.getIntExtra("position", 0);

        /*mDataSource = new DataSource();
        mImageView = findViewById(R.id.image);
        mQuote = findViewById(R.id.quote);
        mImageView.setImageResource(mDataSource.getmPhotoHdPool().
                get(mPosition));
        mQuote.setText(getResources().getString(mDataSource.getmQuotePool()
                .get(mPosition)));*/
    }
}
