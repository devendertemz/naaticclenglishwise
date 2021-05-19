package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.storage.User_login;
import com.englishwise.naaticclenglishwise.util.util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SigninActivity extends AppCompatActivity {
    TextView Tv_Continue;
    EditText Ed_number;
    LoadingDialogs loadingDialogs;

    Snackbar snackbar;

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signin);
        util.blackiteamstatusbar(this, R.color.white);
        Tv_Continue = findViewById(R.id.continue_tv);
        Ed_number = findViewById(R.id.number);
        loadingDialogs = new LoadingDialogs(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        GoogleSignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                loadingDialogs.startLoadingDialogs();

                signIn();

            }
        });

        Tv_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                loadingDialogs.startLoadingDialogs();

                String number = Ed_number.getText().toString();
                if (number.isEmpty() || number.length() < 7) {
                    showSnackbar(v, "Enter a valid Mobile number", Snackbar.LENGTH_SHORT);
                    loadingDialogs.dismissDialog();


                } else {

                    Call<ResponseBody> userlist = ApiClient.getUserService().Generate_otp(number);

                    userlist.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Log.e("failure", response.message());
                            Toast.makeText(SigninActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                            String s = null;

                            if (response.code() == 200) {
                                try {
                                    s = response.body().string();

                                    JSONObject jsonObject = new JSONObject(s);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("otp", jsonObject.getString("otp"));
                                    bundle.putString("message", jsonObject.getString("message"));
                                    bundle.putString("number", number);
                                    bundle.putString("key", "1");
                                    Intent intent = new Intent(SigninActivity.this, OTP_Verification.class);
                                    Toast.makeText(SigninActivity.this, jsonObject.getString("otp") + "", Toast.LENGTH_SHORT).show();

                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    loadingDialogs.dismissDialog();


                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            loadingDialogs.dismissDialog();

                            Log.e("failure", t.getLocalizedMessage());
                            //   Toast.makeText(SigninActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                            //   loadingDialogs.dismissDialog();
                        }
                    });
                }
                /*else {

                    Intent intent = new Intent(SigninActivity.this, OTP_Verification.class);
                    startActivity(intent);
                }*/

            }
        });

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SharedPrefManager.getInstance(this).isLoggedin()) {
            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            loadingDialogs.dismissDialog();

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String personName = "", personGivenName = "", personFamilyName = "", personEmail = "", personId = "";
            Uri personPhoto = null;
            if (acct != null) {
                personName = acct.getDisplayName();
                personGivenName = acct.getGivenName();
                personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                personId = acct.getId();
                personPhoto = acct.getPhotoUrl();

                loadingDialogs.dismissDialog();

                validate_user_byemail(personName, personEmail);

                //Toast.makeText(this, personPhoto + "", Toast.LENGTH_SHORT).show();
            }


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //  Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.d("signInResult:=", e.toString());

            // updateUI(null);
        }
    }


    public void showSnackbar(View view, String message, int duration) {

        Snackbar.make(view, message, duration).
                show();
    }


    private void validate_user_byemail(String personName, String personEmail) {

        loadingDialogs.startLoadingDialogs();

        Call<ResponseBody> userlist = ApiClient.getUserService().validate_user_byEmail(personEmail);

        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(SigninActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.code() == 200) {
                    try {
                        s = response.body().string();
                        Toast.makeText(SigninActivity.this, s + "", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("records");


                        User_login user_login = new User_login(1,
                                jsonObject1.getString("id"),
                                jsonObject1.getString("fullname"),
                                jsonObject1.getString("phone"),
                                jsonObject1.getString("email"),
                                jsonObject1.getString("profile_image"),
                                jsonObject1.getString("language")
                        );

                        SharedPrefManager.getInstance(SigninActivity.this)
                                .saveuser(user_login);
                        loadingDialogs.dismissDialog();


                        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }


                }
                else if (response.code() == 404) {
                    loadingDialogs.dismissDialog();

                    Intent in = new Intent(SigninActivity.this, ProfileActivity.class);
                    in.putExtra("personName", personName);
                    in.putExtra("key", "2");
                    in.putExtra("personEmail", personEmail);
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