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

import com.englishwise.naaticclenglishwise.Adapter.YoutubeVideo_Adapterr;
import com.englishwise.naaticclenglishwise.Modal.Youtube_Model;
import com.englishwise.naaticclenglishwise.ModalResponse.VideoRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity implements ItemClickListenerr {
    Vibrator vibe;
    RecyclerView RecyclerView_Video;

    LoadingDialogs loadingDialogs;

    private ArrayList<VideoRespBean.Datum> data;
    YoutubeVideo_Adapterr youtubeVideo_adapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        //   util.blackiteamstatusbar(this, R.color.gradient_end_color);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        initView();
        get_youtubeVideo();

    }

    private void initView() {

        RecyclerView_Video = findViewById(R.id.RecyclerView_Video);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        RecyclerView_Video.setLayoutManager(manager);
        loadingDialogs = new LoadingDialogs(this);

      /*  mData.add(new videoModelList("09-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        // adapter ini and setup
        mData.add(new videoModelList("11-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        mData.add(new videoModelList("19-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        mData.add(new videoModelList("29-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));


        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                *//*
                Intent intent = new Intent(News.this,PDFActivity.class);

                //intent.putExtra("url",list.get(position).getPdfUrl());
                intent.putExtra("position",position);
                startActivity(intent);*//*
            }
        };
        videoAdapter = new VideoAdapter(this, mData, itemClickListener);
        RecyclerView_Video.setAdapter(videoAdapter);*/
    }

    public void OnBackpress(View view) {
        vibe.vibrate(50);

        onBackPressed();

    }

    private void get_youtubeVideo() {

        loadingDialogs.startLoadingDialogs();


        Call<VideoRespBean> bodyCall = ApiClient.getUserService().Get_YoutubeVideo();

        bodyCall.enqueue(new Callback<VideoRespBean>() {
            @Override
            public void onResponse(Call<VideoRespBean> call, Response<VideoRespBean> response) {
                // Log.e("response", String.valueOf(response.code()));
                //  Toast.makeText(activity, "dgfdgfd", Toast.LENGTH_SHORT).show();

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {


                        loadingDialogs.dismissDialog();
                        VideoRespBean blogRespBean = response.body();


                        // Toast.makeText(BlogActivity.this, response.message() + "", Toast.LENGTH_SHORT).show();
                        data = new ArrayList<VideoRespBean.Datum>(Arrays.asList(blogRespBean.getResults()));


                        youtubeVideo_adapterr = new YoutubeVideo_Adapterr(data, getApplicationContext(), VideoActivity.this);
                        RecyclerView_Video.setItemAnimator(new DefaultItemAnimator());
                        RecyclerView_Video.setAdapter(youtubeVideo_adapterr);


                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<VideoRespBean> call, Throwable t) {
                loadingDialogs.dismissDialog();

                // Toast.makeText(getContext(), t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public void onClick(int position) {

        Youtube_Model youtube_model = new Youtube_Model(data.get(position).getProfileVideoId(),
                data.get(position).getVideoName(),
                data.get(position).getTitle(),
                data.get(position).getThumbnailImage(),
                data.get(position).getVideoUrl());

        Intent intent=new Intent(VideoActivity.this, VideoPlayerActivity.class);
        intent.putExtra("youtube_model",youtube_model);
        startActivity(intent);
       // Toast.makeText(getApplicationContext(), data.get(position).getProfileVideoId()+"", Toast.LENGTH_SHORT).show();

    }
}