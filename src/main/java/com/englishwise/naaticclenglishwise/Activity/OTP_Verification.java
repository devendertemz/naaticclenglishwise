package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.chaos.view.PinView;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.storage.User_login;
import com.englishwise.naaticclenglishwise.util.util;
import com.irozon.sneaker.Sneaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_Verification extends AppCompatActivity {
    TextView otpContinue;
    Vibrator vibe;
    PinView pinView;
    private String OTP, Number, key;
    LoadingDialogs loadingDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);*/
        setContentView(R.layout.activity_o_t_p__verification);

        util.blackiteamstatusbar(this, R.color.white);
        otpContinue = findViewById(R.id.otpContinue);
        pinView = findViewById(R.id.pinview);
        loadingDialogs = new LoadingDialogs(this);
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        getdata();

        otpContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OTP();

            }
        });


    }

    private void getdata() {


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            OTP = bundle.getString("otp");
            Number = bundle.getString("number");
            key = bundle.getString("key");
            Sneaker.with(this)
                    .setTitle(bundle.getString("message"))
                    .setMessage("")
                    .sneakSuccess();
            // Toast.makeText(this, otp+"", Toast.LENGTH_SHORT).show();
        }


    }

    public void OTP() {
        loadingDialogs.startLoadingDialogs();

        vibe.vibrate(50);

        String otp = pinView.getText().toString();
        if (otp.length() < 6) {
            Sneaker.with(this)
                    .setTitle("Enter OTP!")
                    .setMessage("")
                    .sneakError();

            loadingDialogs.dismissDialog();

        } else {
            if (otp.equals(OTP)) {

                Sneaker.with(this)
                        .setTitle("Mobile verification has successfully done")
                        .setMessage("")
                        .sneakSuccess();

                validate_user();

            } else {
                loadingDialogs.dismissDialog();

                Sneaker.with(this)
                        .setTitle("Invalid OTP")
                        .setMessage("")
                        .sneakError();

            }

        }
    }

    public void On_ResendOTP(View view) {

        loadingDialogs.startLoadingDialogs();

        vibe.vibrate(50);


        Call<ResponseBody> userlist = ApiClient.getUserService().Generate_otp(Number);

        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(OTP_Verification.this, response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.code() == 200) {
                    try {
                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        OTP = jsonObject.getString("otp");
                        Sneaker.with(OTP_Verification.this)
                                .setTitle(jsonObject.getString("message"))
                                .setMessage("")
                                .sneakSuccess();
                        Toast.makeText(OTP_Verification.this, jsonObject.getString("otp") + "", Toast.LENGTH_SHORT).show();
                        loadingDialogs.dismissDialog();

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                loadingDialogs.dismissDialog();

                //   Toast.makeText(SigninActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                //   loadingDialogs.dismissDialog();
            }
        });
    }

    public void validate_user() {


        Call<ResponseBody> userlist = ApiClient.getUserService().validate_user_bynumber(Number);

        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(OTP_Verification.this, response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.code() == 200) {
                    try {
                        s = response.body().string();
                        Toast.makeText(OTP_Verification.this, s + "", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("records");


                        User_login user_login = new User_login(1, jsonObject1.getString("id"),
                                jsonObject1.getString("fullname"),
                                jsonObject1.getString("phone")
                                , jsonObject1.getString("email")
                                , jsonObject1.getString("profile_image"),
                                jsonObject1.getString("language"));

                        SharedPrefManager.getInstance(OTP_Verification.this)
                                .saveuser(user_login);

                        loadingDialogs.dismissDialog();

                        Intent intent = new Intent(OTP_Verification.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                } else if (response.code() == 404) {
                    loadingDialogs.dismissDialog();


                    Intent in = new Intent(OTP_Verification.this, ProfileActivity.class);
                    in.putExtra("number", Number);
                    in.putExtra("key", key);


                    startActivity(in);

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                loadingDialogs.dismissDialog();

                //   Toast.makeText(SigninActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                //   loadingDialogs.dismissDialog();
            }
        });
    }

}