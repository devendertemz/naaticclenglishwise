package com.englishwise.naaticclenglishwise.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.storage.User_login;
import com.englishwise.naaticclenglishwise.util.ImagePath;
import com.englishwise.naaticclenglishwise.util.util;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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

public class ProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;

    Vibrator vibe;
    final int REquestPermissionCode = 1000;
    Uri imageUri;
    FrameLayout Opengallery;
    ImageView iv_profile_photo;
    private EditText ET_Name, ET_Email, ET_number;
    TextView selectlanguage_TV;
    LinearLayout LL_number, LL_Email;
    //  Spinner language_Spinner;
    LoadingDialogs loadingDialogs;
    String Language = "Select Language";
    Context context;
    String path = "null";
    public boolean permissionStatus;
    public static final int PERMISSION_REQUEST_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();


    }

    private void initView() {

    /*    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);*/

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
        context = ProfileActivity.this;

        if (!ImagePath.checkPermissionss(context)) {
            ImagePath.requestPermission(this);
        }
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
                //  Sucess("Change language", "All the current data will be lost on changing the language. Do you still want to proceed?");
                ShowLanguage();

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

   /* private boolean checkPermissionss() {
        //Check permission
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;


    }*/

    private void openGallery() {

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

      /*  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);*/
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

        } else if (path.equals("null")) {
            loadingDialogs.dismissDialog();

            Sneaker.with(this)
                    .setTitle("Select  Profile Image")
                    .setMessage("")
                    .sneakError();
        } else {
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part image =
                    MultipartBody.Part.createFormData("profile_image", file.getName(), requestFile);

            RequestBody namee = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody emaill = RequestBody.create(MediaType.parse("multipart/form-data"), email);
            RequestBody Numberr = RequestBody.create(MediaType.parse("multipart/form-data"), Number);
            RequestBody Languagee = RequestBody.create(MediaType.parse("multipart/form-data"), Language);

            Call<ResponseBody> userlist = ApiClient.getUserService().registration(namee, emaill, Numberr, Languagee, image);


            userlist.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    Toast.makeText(ProfileActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                    String s = null;

                    if (response.code() == 200) {
                        try {
                            s = response.body().string();
                            Log.e("responsee", s);
                            //  Toast.makeText(ProfileActivity.this, s + "", Toast.LENGTH_SHORT).show();
                            JSONObject jsonObject = new JSONObject(s);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("records");


                            User_login user_login = new User_login(1, jsonObject1.getString("id"),
                                    jsonObject1.getString("fullname"),
                                    jsonObject1.getString("phone")
                                    , jsonObject1.getString("email")
                                    , getResources().getString(R.string.Proflie_url) + jsonObject1.getString("profile_image_name"),
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


    public void ShowLanguage() {

        LayoutInflater inflater = LayoutInflater.from(this);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.languagedialog, null);
        ImageView cancel_Iv;
        TextView Hindi_TV, Punjabi_TV, Telugu_TV, Nepaese_TV, Spanish_TV;

        cancel_Iv = view.findViewById(R.id.cancel_Iv);
        Hindi_TV = view.findViewById(R.id.Hindi_TV);

        Punjabi_TV = view.findViewById(R.id.Punjabi_TV);
        Telugu_TV = view.findViewById(R.id.Telugu_TV);
        Nepaese_TV = view.findViewById(R.id.Nepaese_TV);
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


        Punjabi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectlanguage_TV.setText(Punjabi_TV.getText().toString());
                Language = "pa";

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

 /*   private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        }, REquestPermissionCode);


    }*/

}