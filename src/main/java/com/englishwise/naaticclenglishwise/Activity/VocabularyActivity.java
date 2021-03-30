package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Adapter.Vocabulary_Adapterr;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;

import com.englishwise.naaticclenglishwise.util.util;
import com.trendyol.bubblescrollbarlib.BubbleScrollBar;
import com.trendyol.bubblescrollbarlib.BubbleTextProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class VocabularyActivity extends AppCompatActivity {
    Vibrator vibe;
    RecyclerView Vocabulary_RV;
    BubbleScrollBar fastscroll;
    Vocabulary_Adapterr vocabulary_adapterrrrrr;
    List<VocabularyModel> mDataaaaaaaaa;
    LoadingDialogs loadingDialogs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        util.blackiteamstatusbar(this, R.color.gradient_end_color);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        loadingDialogs = new LoadingDialogs(this);
        mDataaaaaaaaa= new ArrayList<>();
        Vocabulary_RV = findViewById(R.id.Vocabulary_RV);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        Vocabulary_RV.setLayoutManager(manager);
        read_vocabulary();

/*
      for (int i=0;i<1;i++)
        {
            mDataaaaaaaaa.add(new VocabularyModel("GOOD","अच्छा"));
        }

        vocabulary_adapterrrrrr = new Vocabulary_Adapterr(this,mDataaaaaaaaa);
        Vocabulary_RV.setAdapter(vocabulary_adapterrrrrr);
        fastscroll.attachToRecyclerView(Vocabulary_RV);
        fastscroll.setBubbleTextProvider(new BubbleTextProvider() {
            @Override
            public String provideBubbleText(int i) {
                return vocabulary_adapterrrrrr.mData.get(i).getEnglis().substring(0,1);
            }
        });*/
        // adapter ini and setup

    }



    private void read_vocabulary() {



        loadingDialogs.startLoadingDialogs();



        Call<ResponseBody> userlist = ApiClient.getUserService().read_vocabulary(SharedPrefManager.getInstans(getApplicationContext()).getlanguage());

        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(VocabularyActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.code() == 200) {
                    try {
                        s = response.body().string();
                        JSONObject jsonObject=new JSONObject(s);
                        String count=jsonObject.getString("count");
                        Toast.makeText(VocabularyActivity.this, count+"", Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray=jsonObject.getJSONArray("records");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String english_word=jsonObject1.getString("english_word");
                            String translated_word=jsonObject1.getString("translated_word");
                            mDataaaaaaaaa.add(new VocabularyModel(english_word,translated_word));



                        }

                        vocabulary_adapterrrrrr = new Vocabulary_Adapterr(VocabularyActivity.this, mDataaaaaaaaa);
                        Vocabulary_RV.setAdapter(vocabulary_adapterrrrrr);
                        fastscroll = findViewById(R.id.fastscroll);

                        fastscroll.attachToRecyclerView(Vocabulary_RV);
                        fastscroll.setBubbleTextProvider(new BubbleTextProvider() {
                            @Override
                            public String provideBubbleText(int i) {
                                return vocabulary_adapterrrrrr.mData.get(i).getEnglis().substring(0,1);
                            }
                        });

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


    public void OnBackpress(View view) {
        vibe.vibrate(50);

        onBackPressed();

    }
}