package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Adapter.BlogAdapter;
import com.englishwise.naaticclenglishwise.Modal.BlogModel;
import com.englishwise.naaticclenglishwise.ModalResponse.BlogRespBean;
import com.englishwise.naaticclenglishwise.ModalResponse.VideoRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.util.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity implements ItemClickListenerr {
    Vibrator vibe;
    LoadingDialogs loadingDialogs;
    private ArrayList<BlogRespBean.Datum> data;
    BlogAdapter blogAdapter;
    RecyclerView RecyclerView_Blog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        RecyclerView_Blog = findViewById(R.id.RecyclerView_Blog);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        loadingDialogs = new LoadingDialogs(this);
        getBlog();

    }

    public void OnBackpress(View view) {
        vibe.vibrate(50);

        onBackPressed();
    }


    private void getBlog() {

        loadingDialogs.startLoadingDialogs();


        Call<BlogRespBean> bodyCall = ApiClient.getUserService().getBlog();

        bodyCall.enqueue(new Callback<BlogRespBean>() {
            @Override
            public void onResponse(Call<BlogRespBean> call, Response<BlogRespBean> response) {
                //     Toast.makeText(BlogActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {
                        BlogRespBean blogRespBean = response.body();

                        // Toast.makeText(BlogActivity.this, response.message() + "", Toast.LENGTH_SHORT).show();
                        data = new ArrayList<BlogRespBean.Datum>(Arrays.asList(blogRespBean.getResults()));

                        blogAdapter = new BlogAdapter(data, getApplicationContext(), BlogActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        RecyclerView_Blog.setLayoutManager(mLayoutManager);
                        RecyclerView_Blog.setItemAnimator(new DefaultItemAnimator());
                        RecyclerView_Blog.setAdapter(blogAdapter);
                        loadingDialogs.dismissDialog();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<BlogRespBean> call, Throwable t) {
                loadingDialogs.dismissDialog();

                Toast.makeText(BlogActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onClick(int position) {

        BlogModel blogModel = new BlogModel(data.get(position).getBlogId(), data.get(position).getImage(),
                data.get(position).getTitle(), data.get(position).getShortDescription(), data.get(position).getLongDescription());

        Intent intent = new Intent(BlogActivity.this, BlogDescrpiption_Activity.class);
        intent.putExtra("blogModel", blogModel);
        startActivity(intent);


        Toast.makeText(this, data.get(position).getTitle() +
                "", Toast.LENGTH_SHORT).show();

    }
}