package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.util.util;

public class BlogActivity extends AppCompatActivity {
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //dev

    }

    public void OnBackpress(View view) {
        vibe.vibrate(50);

        onBackPressed();
    }
}