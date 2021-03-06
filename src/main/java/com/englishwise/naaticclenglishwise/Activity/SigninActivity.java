package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import static com.englishwise.naaticclenglishwise.R.string.message_enter_a_valid_Mobile_number;

public class SigninActivity extends AppCompatActivity {
    TextView Tv_Continue;
    EditText Ed_number;

    Snackbar snackbar;

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_signin);
        com.englishwise.naaticclenglishwise.Activity.util.util.blackiteamstatusbar(this, R.color.white);
        Tv_Continue = findViewById(R.id.continue_tv);
        Ed_number = findViewById(R.id.number);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        GoogleSignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        Tv_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = Ed_number.getText().toString();
                if (number.isEmpty()) {
                showSnackbar(v, "Enter a valid Mobile number", Snackbar.LENGTH_SHORT);



                } else {

                    Intent intent = new Intent(SigninActivity.this, OTP_Verification.class);
                    startActivity(intent);
                }
            }
        });

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

                Toast.makeText(this, personPhoto + "", Toast.LENGTH_SHORT).show();
            }
            Intent in = new Intent(SigninActivity.this, ProfileActivity.class);
            in.putExtra("personName", personName);
            in.putExtra("personGivenName", personGivenName);
            in.putExtra("personFamilyName", personFamilyName);
            in.putExtra("personEmail", personEmail);
            in.putExtra("personId", personId);
            in.putExtra("personPhoto", personPhoto);
            startActivity(in);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);

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
}