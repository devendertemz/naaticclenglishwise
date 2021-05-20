package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;

public class EditProfile_Activity extends AppCompatActivity {

    ImageView Day_IV, Night_IV;
    TextView Tv_Email, TV_Number, Tv_Language;
    EditText Ed_Name;


    FrameLayout Opengallery;
    ImageView iv_profile_photo;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
        //util.blackiteamstatusbar(this, R.color.gradient_end_color);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        InitView();


    }

    private void InitView() {

     /*   Log.e("user", SharedPrefManager.getInstans(getApplicationContext()).getNumber() +
                SharedPrefManager.getInstans(getApplicationContext()).getemail() +
                SharedPrefManager.getInstans(getApplicationContext()).getfullname() +
                SharedPrefManager.getInstans(getApplicationContext()).getlanguage() +
                SharedPrefManager.getInstans(getApplicationContext()).getprofileImage() +
                SharedPrefManager.getInstans(getApplicationContext()).getUserId()


        );*/

        Opengallery = findViewById(R.id.Opengallery);
        iv_profile_photo = findViewById(R.id.iv_profile_photo);

        Night_IV = findViewById(R.id.Night_IV);
        Day_IV = findViewById(R.id.Day_IV);

        TV_Number = findViewById(R.id.TV_Number);

        Tv_Email = findViewById(R.id.Tv_Email);
        Ed_Name = findViewById(R.id.Ed_Name);
        Tv_Language = findViewById(R.id.Tv_Language);

        try {

            Glide.with(this)
                    .load("https://misfitamigos.com/naticcl_api/user_guide/_images/"+SharedPrefManager.getInstans(getApplicationContext()).getprofileImage())
                    .into(iv_profile_photo);
        }catch (Exception e)
        {

        }
        TV_Number.setText(SharedPrefManager.getInstans(getApplicationContext()).getNumber());
        Tv_Email.setText(SharedPrefManager.getInstans(getApplicationContext()).getemail());
        Ed_Name.setText(SharedPrefManager.getInstans(getApplicationContext()).getfullname());
        if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("hi")) {
            Tv_Language.setText("Hindi");

        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("ta")) {
            Tv_Language.setText("Tamil");
        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("ur")) {
            Tv_Language.setText("Urdu");
        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("ml")) {
            Tv_Language.setText("Malayalam");

        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("te")) {
            Tv_Language.setText("Telugu");

        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("ne")) {
            Tv_Language.setText("Nepaese");

        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("gu")) {
            Tv_Language.setText("Gujarati");

        } else if (SharedPrefManager.getInstans(getApplicationContext()).getlanguage().trim().equals("es")) {
            Tv_Language.setText("Spanish");

        }

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

        Opengallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
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
}