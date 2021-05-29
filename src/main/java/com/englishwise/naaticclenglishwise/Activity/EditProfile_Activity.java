package com.englishwise.naaticclenglishwise.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.storage.User_login;
import com.englishwise.naaticclenglishwise.util.ImagePath;
import com.irozon.sneaker.Sneaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile_Activity extends AppCompatActivity {

    ImageView Day_IV, Night_IV;
    TextView Tv_Email, TV_Number, Tv_Language;
    EditText Ed_Name;
    Context context;
    String path = "null";

    FrameLayout Opengallery;
    ImageView iv_profile_photo;
    Uri imageUri;
    private static final int PICK_IMAGE = 100;
    LoadingDialogs loadingDialogs;
    Vibrator vibe;


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
        context = this;
        vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        loadingDialogs = new LoadingDialogs(this);

        if (!ImagePath.checkPermissionss(context)) {
            ImagePath.requestPermission(this);
        }
        try {

            Glide.with(this)
                    .load(SharedPrefManager.getInstans(this.getApplicationContext()).getprofileImage())
                    .into(iv_profile_photo);
        } catch (Exception e) {

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
        vibe.vibrate(50);
        UpdateProfile();

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (data == null)
                return;

            Uri uri = data.getData();
            System.out.println("urii  " + uri + " " + uri.getPath());
            path = ImagePath.getPath(context, uri);
            System.out.println("urii path " + path);

            imageUri = data.getData();
            iv_profile_photo.setImageURI(imageUri);
          /*  if(path!=null && !path.equals("")) {
                File file = new File(path);
                uploadImage(file);
            }*/
        }
    }


    public void UpdateProfile() {

        if (path.equals("null")) {

            Sneaker.with(this)
                    .setTitle("Select  Profile Image")
                    .setMessage("")
                    .sneakError();
        } else {
            loadingDialogs.startLoadingDialogs();

            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("profile_image", file.getName(), requestFile);


            RequestBody namee = RequestBody.create(MediaType.parse("multipart/form-data"), Ed_Name.getText().toString().trim());
            RequestBody emaill = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefManager.getInstans(getApplicationContext()).getemail());
            RequestBody Numberr = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefManager.getInstans(getApplicationContext()).getNumber());
            RequestBody Languagee = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefManager.getInstans(getApplicationContext()).getlanguage());
            RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), SharedPrefManager.getInstans(getApplicationContext()).getUserId());

            Call<ResponseBody> userlist = ApiClient.getUserService().update_profile(namee, emaill, Numberr, Languagee, id, image);


            userlist.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    //  Toast.makeText(EditProfile_Activity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                    String s = null;

                    if (response.code() == 200) {
                        try {
                            s = response.body().string();
                            Log.e("responsee", s);
                            // Toast.makeText(EditProfile_Activity.this, s + "", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");


                            User_login user_login = new User_login(1, jsonObject1.getString("id"),
                                    jsonObject1.getString("fullname"),
                                    jsonObject1.getString("phone")
                                    , jsonObject1.getString("email")
                                    , getResources().getString(R.string.Proflie_url) + jsonObject1.getString("profile_image"),
                                    jsonObject1.getString("language"));

                            SharedPrefManager.getInstance(EditProfile_Activity.this)
                                    .saveuser(user_login);

                            loadingDialogs.dismissDialog();

                            Toast.makeText(EditProfile_Activity.this, "Updated Profile", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(EditProfile_Activity.this, MainActivity.class);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(in);

                   /*     Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        startActivity(intent);*/


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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("requestCode" + requestCode);
        switch (requestCode) {
            case ImagePath.REquestPermissionCode:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissin Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permissin Deined", Toast.LENGTH_SHORT).show();
                }


        }

    }

}