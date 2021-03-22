package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.MainActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.util.util;

public class EditProfile_Activity extends AppCompatActivity {

    ImageView Day_IV,Night_IV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        InitView();


    }

    private void InitView() {
        Night_IV=findViewById(R.id.Night_IV);
        Day_IV=findViewById(R.id.Day_IV);
        Night_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day_IV.setVisibility(View.VISIBLE);
                Night_IV.setVisibility(View.GONE);

            }
        });

        Day_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Night_IV.setVisibility(View.VISIBLE);

                Day_IV.setVisibility(View.GONE);
            }
        });
    }

    public void OnBackpress(View view) {
        onBackPressed();
    }

    public void OnClick_SubmitProfile(View view) {
        Toast.makeText(this, "Updated Profile", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }


}