package com.englishwise.naaticclenglishwise.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Modal.BlogModel;
import com.englishwise.naaticclenglishwise.Modal.Youtube_Model;
import com.englishwise.naaticclenglishwise.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoPlayerActivity extends AppCompatActivity {
    public Youtube_Model youtube_model;
    YouTubePlayerView youTubePlayerView;
    TextView title;
    String videoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        title = findViewById(R.id.title);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        youtube_model = (Youtube_Model) getIntent().getSerializableExtra("youtube_model");
        //Toast.makeText(this, youtube_model.profileVideoId + "", Toast.LENGTH_SHORT).show();
        if (youtube_model.key.equals("API")) {
            initPictureInPicture(youTubePlayerView);
            getLifecycle().addObserver(youTubePlayerView);
            title.setText(youtube_model.title);
            videoId = youtube_model.videoUrl.trim();


        } else if (youtube_model.key.equals("LOCAL")) {
            Toast.makeText(this, "local", Toast.LENGTH_SHORT).show();
            initPictureInPicture(youTubePlayerView);
            getLifecycle().addObserver(youTubePlayerView);
            title.setText(youtube_model.title);
            videoId = youtube_model.videoUrl.trim();

        }
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(videoId, 0);
            }
        });


    }


    private void initPictureInPicture(YouTubePlayerView youTubePlayerView) {
        ImageView pictureInPictureIcon = new ImageView(this);
        pictureInPictureIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_picture_in_picture_24dp));

        pictureInPictureIcon.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                boolean supportsPIP = getPackageManager().hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE);
                if (supportsPIP)
                    enterPictureInPictureMode();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Can't enter picture in picture mode")
                        .setMessage("In order to enter picture in picture mode you need a SDK version >= N.")
                        .show();
            }
        });

        youTubePlayerView.getPlayerUiController().addView(pictureInPictureIcon);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        if (isInPictureInPictureMode) {
            youTubePlayerView.enterFullScreen();
            youTubePlayerView.getPlayerUiController().showUi(false);
        } else {
            youTubePlayerView.exitFullScreen();
            youTubePlayerView.getPlayerUiController().showUi(true);
        }
    }

    public void OnBackpress(View view) {
        onBackPressed();
    }
}
