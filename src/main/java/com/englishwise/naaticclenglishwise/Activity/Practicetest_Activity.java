package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Adapter.ListMocktestAdapter;
import com.englishwise.naaticclenglishwise.Adapter.MockTestList_Adapter;
import com.englishwise.naaticclenglishwise.Adapter.Vocabulary_Adapterr;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.ModalResponse.MockRespBean;
import com.englishwise.naaticclenglishwise.ModalResponse.PracticetestRepo;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.util.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Practicetest_Activity extends AppCompatActivity implements ItemClickListenerr {
    String id,compareid;
    TextView practicetest;
    RecyclerView RV_practicetest;
    MockTestList_Adapter mockTestList_adapter;
    List<PracticetestRepo> practicetestRepoList;
    LoadingDialogs loadingDialogs;

    PracticetestRepo practicetestRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicetest_);
        InitView();

    }

    private void InitView() {
       // util.blackiteamstatusbar(this, R.color.gradient_end_color);

        practicetest = findViewById(R.id.practicetest);
        RV_practicetest = findViewById(R.id.RV_practicetest);
        practicetestRepoList = new ArrayList<>();
        loadingDialogs = new LoadingDialogs(this);
        GridLayoutManager manager = new GridLayoutManager(this, 1,GridLayoutManager.VERTICAL, false);
        RV_practicetest.setLayoutManager(manager);

        try {

            if (getIntent() != null) {
                id = getIntent().getExtras().getString("id");
                practicetest.setText(getIntent().getExtras().getString("CategoryName"));
                ReadMockpracticetest();

            }

        } catch (Exception e) {

        }



    }

    private void ReadMockpracticetest() {
        //Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
        loadingDialogs.startLoadingDialogs();

        Call<ResponseBody> user = ApiClient.getUserService().Read_practicetestt(id);
        user.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    try {
                        s = response.body().string();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            compareid=  jsonObject1.getString("id");
                            practicetestRepo = new PracticetestRepo(
                                    jsonObject1.getString("id"),
                                    jsonObject1.getString("mid"),
                                    jsonObject1.getString("question"),
                                    jsonObject1.getString("answer"),
                                    jsonObject1.getString("create_at")
                            );
                            practicetestRepoList.add(practicetestRepo);

                            mockTestList_adapter = new MockTestList_Adapter(Practicetest_Activity.this, practicetestRepoList,Practicetest_Activity.this);
                            RV_practicetest.setAdapter(mockTestList_adapter);


                            loadingDialogs.dismissDialog();

                        }
                        //Toast.makeText(Practicetest_Activity.this, jsonObject.getString("message")+"", Toast.LENGTH_SHORT).show();


                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                }

                loadingDialogs.dismissDialog();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Practicetest_Activity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();


            }
        });








    }


    public void OnBackpress(View view) {

        onBackPressed();


    }

    @Override
    public void onClick(int position) {

        String id = practicetestRepoList.get(position).getId();
        Intent intent=new Intent(Practicetest_Activity.this,MockTestActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("compareid",compareid);


        startActivity(intent);

     Toast.makeText(this, ""+compareid, Toast.LENGTH_SHORT).show();

    }
}