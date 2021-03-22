package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import com.englishwise.naaticclenglishwise.Adapter.HomeVideoAdapter;
import com.englishwise.naaticclenglishwise.Adapter.VideoAdapter;
import com.englishwise.naaticclenglishwise.Modal.videoModel;
import com.englishwise.naaticclenglishwise.Modal.videoModelList;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListener;
import com.englishwise.naaticclenglishwise.util.util;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {
    Vibrator vibe;
    RecyclerView RecyclerView_Video;
    VideoAdapter videoAdapter;
    List<videoModelList> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        initView();


    }

    private void initView() {
        mData = new ArrayList<>();

        RecyclerView_Video = findViewById(R.id.RecyclerView_Video);
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        RecyclerView_Video.setLayoutManager(manager);

        mData.add(new videoModelList("09-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        // adapter ini and setup
        mData.add(new videoModelList("11-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        mData.add(new videoModelList("19-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));
        mData.add(new videoModelList("29-jan-2020", "Episode 4: education | Naati ccl", "Punjabi |Exam Topic |Dialogue", R.drawable.profile));


        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                /*
                Intent intent = new Intent(News.this,PDFActivity.class);

                //intent.putExtra("url",list.get(position).getPdfUrl());
                intent.putExtra("position",position);
                startActivity(intent);*/
            }
        };
        videoAdapter = new VideoAdapter(this, mData, itemClickListener);
        RecyclerView_Video.setAdapter(videoAdapter);
    }

    public void OnBackpress(View view) {
        vibe.vibrate(50);

        onBackPressed();

    }
}