package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.englishwise.naaticclenglishwise.R;

public class Intro_to_Natti_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_to__natti_);
    }

    public void OnBackpress(View view) {
        onBackPressed();
    }
}