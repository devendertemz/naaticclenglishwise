package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.MainActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.util.util;

public class EditProfile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    public void OnBackpress(View view) {
        onBackPressed();
    }

    public void OnClick_SubmitProfile(View view) {
        Toast.makeText(this, "Updated Profile", Toast.LENGTH_SHORT).show();
        Intent in=new Intent(this, MainActivity.class);
        startActivity(in);
    }
}