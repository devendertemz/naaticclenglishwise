package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.chaos.view.PinView;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.util.util;

public class OTP_Verification extends AppCompatActivity {
    TextView otpContinue, resendotp;

    PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_o_t_p__verification);

      util.blackiteamstatusbar(this, R.color.white);
        otpContinue = findViewById(R.id.otpContinue);
        resendotp = findViewById(R.id.resendotp);
        pinView = findViewById(R.id.pinview);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


        otpContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTP();
                vibe.vibrate(50);

            }
        });



    }

    public void OTP()
    {

        String otp=pinView.getText().toString();
        if (otp.length()<6)
        {
            Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show();

        }else
        {
            Intent in = new Intent(OTP_Verification.this, ProfileActivity.class);
            startActivity(in);
            Toast.makeText(OTP_Verification.this, otp+"", Toast.LENGTH_SHORT).show();

        }
    }
}