package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.MainActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.storage.User_login;
import com.englishwise.naaticclenglishwise.util.util;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.irozon.sneaker.Sneaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    Vibrator vibe;

    Uri imageUri;
    FrameLayout Opengallery;
    ImageView iv_profile_photo;
    private EditText ET_Name, ET_Email, ET_number;
    TextView selectlanguage_TV;
    LinearLayout LL_number, LL_Email;
    //  Spinner language_Spinner;
    LoadingDialogs loadingDialogs;
    String Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


    }

    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_profile);
        util.blackiteamstatusbar(this, R.color.white);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        loadingDialogs = new LoadingDialogs(this);

        selectlanguage_TV = findViewById(R.id.selectlanguage_TV);
        ET_Name = findViewById(R.id.ET_Name);
        ET_Email = findViewById(R.id.ET_Email);
        ET_number = findViewById(R.id.ET_number);
        Opengallery = findViewById(R.id.Opengallery);
        LL_number = findViewById(R.id.LL_number);
        LL_Email = findViewById(R.id.LL_Email);
        iv_profile_photo = findViewById(R.id.iv_profile_photo);


        GetGoogleaccess();

        Opengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        selectlanguage_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sucess("Change language", "Once you change the language,you will lost all the current data.Are you sure you want to change the language ?");

            }
        });


    }

    private void GetGoogleaccess() {

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String key = extras.getString("key");

            if (key.equals("2")) {
                ET_Name.setText(extras.getString("personName"));
                ET_Email.setText(extras.getString("personEmail"));
                LL_number.setVisibility(View.VISIBLE);
                LL_Email.setVisibility(View.GONE);
            } else {
                LL_number.setVisibility(View.GONE);
                LL_Email.setVisibility(View.VISIBLE);
                String number = getIntent().getStringExtra("number");

                ET_number.setText(number);
                Toast.makeText(this, number + "", Toast.LENGTH_SHORT).show();
            }


            //The key argument here must match that used in the other activity
        } else {
            //  Toast.makeText(this, "Not get", Toast.LENGTH_SHORT).show();
        }


    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            iv_profile_photo.setImageURI(imageUri);
        }
    }

    public void profile_continue(View view) {
        loadingDialogs.startLoadingDialogs();

        vibe.vibrate(50);
        String name = ET_Name.getText().toString().trim();
        String email = ET_Email.getText().toString().trim();
       // String Language = selectlanguage_TV.getText().toString().trim();
        String Number = ET_number.getText().toString().trim();

        if (name.isEmpty()) {
            Sneaker.with(this)
                    .setTitle("Enter your full name!")
                    .setMessage("")
                    .sneakError();
            loadingDialogs.dismissDialog();

        } else if (email.isEmpty()) {
            loadingDialogs.dismissDialog();

            Sneaker.with(this)
                    .setTitle("Enter your email!")
                    .setMessage("")
                    .sneakError();
        } else if (Language.equals("Select Language")) {
            loadingDialogs.dismissDialog();

            Sneaker.with(this)
                    .setTitle("Select Language!")
                    .setMessage("")
                    .sneakError();

        } else if (Number.isEmpty()) {
            loadingDialogs.dismissDialog();

            Sneaker.with(this)
                    .setTitle("Enter number!")
                    .setMessage("")
                    .sneakError();

        } else {


            Call<ResponseBody> userlist = ApiClient.getUserService().registration(name, email, Number, Language);

            userlist.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    Toast.makeText(ProfileActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                    String s = null;

                    if (response.code() == 200) {
                        try {
                            s = response.body().string();
                            Toast.makeText(ProfileActivity.this, s + "", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("records");


                            User_login user_login = new User_login(1, jsonObject1.getString("id"),
                                    jsonObject1.getString("fullname"),
                                    jsonObject1.getString("phone")
                                    , jsonObject1.getString("email")
                                    , jsonObject1.getString("profile_image"),
                                    jsonObject1.getString("language"));

                            SharedPrefManager.getInstance(ProfileActivity.this)
                                    .saveuser(user_login);
                            loadingDialogs.dismissDialog();


                            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);


                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("failure", t.getLocalizedMessage());
                    //   Toast.makeText(SigninActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                    //   loadingDialogs.dismissDialog();
                    loadingDialogs.dismissDialog();

                }
            });

        }
    }


    public void Sucess(String titlee, String dess) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.customdialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button read_btn = view.findViewById(R.id.read_btn);
        TextView title = view.findViewById(R.id.title);

        TextView des = view.findViewById(R.id.message);

        title.setText(titlee);
        des.setText(dess);
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                ShowLanguage();
                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();
    }

    public void ShowLanguage() {

        LayoutInflater inflater = LayoutInflater.from(this);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.languagedialog, null);

        ImageView cancel_Iv;
        TextView Hindi_TV, Tamil_TV, Urdu_TV, Punjabi_TV, Malayalam_TV, Telugu_TV, Nepaese_TV, Gujarati_TV, Spanish_TV;

        cancel_Iv = view.findViewById(R.id.cancel_Iv);
        Hindi_TV = view.findViewById(R.id.Hindi_TV);
        Tamil_TV = view.findViewById(R.id.Tamil_TV);
        Urdu_TV = view.findViewById(R.id.Urdu_TV);
        Punjabi_TV = view.findViewById(R.id.Punjabi_TV);
        Malayalam_TV = view.findViewById(R.id.Malayalam_TV);
        Telugu_TV = view.findViewById(R.id.Telugu_TV);
        Nepaese_TV = view.findViewById(R.id.Nepaese_TV);
        Gujarati_TV = view.findViewById(R.id.Gujarati_TV);
        Spanish_TV = view.findViewById(R.id.Spanish_TV);

        cancel_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

            }
        });
        Hindi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hindi_TV.setTypeface(Hindi_TV.getTypeface(), Typeface.BOLD);
                Hindi_TV.setTextColor(Color.parseColor("#ff0000"));
                selectlanguage_TV.setText(Hindi_TV.getText().toString());
                Language = "hi";
                dialog.cancel();

            }
        });
        Tamil_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tamil_TV.setTypeface(Hindi_TV.getTypeface(), Typeface.BOLD);
                Tamil_TV.setTextColor(Color.parseColor("#ff0000"));
                selectlanguage_TV.setText(Tamil_TV.getText().toString());
                Language = "ta";

                dialog.cancel();

            }
        });
        Urdu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Urdu_TV.getText().toString());
                Language = "ur";
                dialog.cancel();

            }
        });
        Punjabi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Punjabi_TV.getText().toString());
                //Language="ur";

                dialog.cancel();

            }
        });
        Malayalam_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Malayalam_TV.getText().toString());
                Language = "ml";

                dialog.cancel();

            }
        });
        Telugu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Telugu_TV.getText().toString());
                Language = "te";

                dialog.cancel();

            }
        });
        Nepaese_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Nepaese_TV.getText().toString());

                Language = "ne";
                dialog.cancel();

            }
        });
        Gujarati_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Gujarati_TV.getText().toString());
                Language = "gu";

                dialog.cancel();

            }
        });


        Spanish_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Spanish_TV.getText().toString());
                Language = "es";

                dialog.cancel();

            }
        });
        /* AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();
*/


        dialog.setContentView(view);
        dialog.show();


       /* title.setText(titlee);
        des.setText(dess);*/

        // alertDialog.show();
    }
}