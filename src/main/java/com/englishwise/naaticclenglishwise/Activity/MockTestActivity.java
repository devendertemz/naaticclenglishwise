package com.englishwise.naaticclenglishwise.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.R;

import com.englishwise.naaticclenglishwise.dialog.Customdialog;
import com.englishwise.naaticclenglishwise.util.util;
import com.ohoussein.playpause.PlayPauseView;
import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


public class MockTestActivity extends AppCompatActivity {

    TextView TV_question, TV_recording, TV_Answer, TV_questionTime, TV_questionTotalTime, Tv_recordingime, TV_AnswerTime, TV_AnswerTotalTime;
    View question_layout, recoding_layout, answer_layout, RecodingPlay_Layout;
    PlayPauseView view, answer_play_pause_view;
    public String count = "0", count_recorder = "0";

    TextView TV_questiondialog;
    Customdialog customdialog;
    CountDownTimer QuestionCountDownTimer, recordingCountDownTimer, AnswerCountDownTimer;
    RippleBackground rippleBackground;
    ImageView imageView;
    // SeekBar seekbar;


    private Handler handler = new Handler();
    private Handler answerhandler = new Handler();
    private MediaPlayer mediaPlayer, answerMediaPlayer;


    private boolean isRecording = false;

    private String recordPermission = Manifest.permission.RECORD_AUDIO;
    private int PERMISSION_CODE = 21;
    private MediaRecorder mediaRecorder;
    private String recordFile;
    private Chronometer timer;


    String pathSave;
    MediaRecorder mediaRecorderr;
    MediaPlayer mediaPlayerr;

    final int REquestPermissionCode = 1000;
    TextView startvoiceplay;


    // private TextView TV_VoiceRecodingTime,TV_voice_recordingTotalTime;
    private PlayPauseView VoiceReording_ppv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_test);
        initView();


    }

    private void initView() {
        if (!checkPermissionss()) {
            requestPermission();
        }
        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        customdialog = new Customdialog(MockTestActivity.this);
        mediaPlayer = new MediaPlayer();
        answerMediaPlayer = new MediaPlayer();
        TV_question = findViewById(R.id.TV_question);
        TV_recording = findViewById(R.id.TV_recording);
        TV_Answer = findViewById(R.id.TV_Answer);
        TV_questionTime = findViewById(R.id.TV_questionTime);
        TV_questionTotalTime = findViewById(R.id.TV_questionTotalTime);
        Tv_recordingime = findViewById(R.id.Tv_recordingime);
        TV_AnswerTime = findViewById(R.id.TV_AnswerTime);
        TV_AnswerTotalTime = findViewById(R.id.TV_AnswerTotalTime);
        TV_questiondialog = findViewById(R.id.TV_questiondialog);

        question_layout = findViewById(R.id.question_layout);

        recoding_layout = findViewById(R.id.recoding_layout);

        answer_layout = findViewById(R.id.answer_layout);

        view = (PlayPauseView) findViewById(R.id.play_pause_view);
        answer_play_pause_view = (PlayPauseView) findViewById(R.id.answer_play_pause_view);

        startvoiceplay = findViewById(R.id.startvoiceplay);

        rippleBackground = (RippleBackground) findViewById(R.id.content);
        // imageView = (ImageView) findViewById(R.id.centerImage);

        timer = findViewById(R.id.record_timer);


        RecodingPlay_Layout = findViewById(R.id.RecodingPlay_Layout);
        VoiceReording_ppv = findViewById(R.id.VoiceReording_ppv);


        StartQuestion();
        TV_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!count.equals("0")) {

                    playingQuestion();
                }

            }
        });
        VoiceReording_ppv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecord();
            }
        });
        TV_recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mediaPlayer.isPlaying()) {
                    // mediaPlayer.stop();
                    // mediaPlayer.reset();
                    //  setRecoding_layout();

                    // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {

                    setRecoding_layout();

                }
            }
        });
    }


    private void StartQuestion() {
        prepareMediaPlayer();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    view.toggle();
                    view.isPlay();
                    // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {
                    mediaPlayer.start();

                    //imageplayPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                    view.toggle();
                    updateSeekbar();
                }
            }
        });

        playingQuestion();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);


                count = "1";
                setRecoding_layout();
                /*
                TV_questionTime.setText("0");
                TV_questionTotalTime.setText("0");
                mediaPlayer.reset();
                prepareMediaPlayer();
            */
            }
        });
    }


    private void playingQuestion() {

        if (count.equals("1")) {
            question_layout.setVisibility(View.VISIBLE);
            TV_recording.setVisibility(View.VISIBLE);
            recoding_layout.setVisibility(View.GONE);

            TV_recording.setBackgroundResource(R.drawable.mocktestbg_gray);
            TV_recording.setTextColor(Color.parseColor("#696969"));

            TV_question.setBackgroundResource(R.drawable.mocktestbgbluee);
            TV_question.setTextColor(Color.WHITE);

            if (mediaPlayer.isPlaying()) {
                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                view.toggle();
            } else {

                TV_Answer.setBackgroundResource(R.drawable.mocktestbg_gray);
                TV_Answer.setTextColor(Color.parseColor("#696969"));

                TV_question.setBackgroundResource(R.drawable.mocktestbgbluee);
                TV_question.setTextColor(Color.WHITE);
                answer_layout.setVisibility(View.GONE);
                mediaPlayer.start();
                view.toggle();

                updateSeekbar();
            }
        } else {
            TV_question.setBackgroundResource(R.drawable.mocktestbgbluee);
            TV_question.setTextColor(Color.WHITE);

            if (mediaPlayer.isPlaying()) {
                handler.removeCallbacks(updater);
                mediaPlayer.pause();
                // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                view.toggle();
            } else {
                mediaPlayer.start();
                view.toggle();

                updateSeekbar();
            }
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            TV_questionTime.setText(milisecondTotime(currentDuration));
        }
    };

    @SuppressLint("SetTextI18n")
    private void prepareMediaPlayer() {


        try {
            mediaPlayer.setDataSource("http://misfitamigos.com/naticcl_englishwise/mock_test_data/hindi/answers/Aged_care/Aged_Care_Centre_Hindi_2.mp3");
            mediaPlayer.prepare();
            TV_questionTotalTime.setText("/" + milisecondTotime(mediaPlayer.getDuration()) + " Sec");
            Log.d("musicPlayerTimehere", String.valueOf(mediaPlayer.getDuration()));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String milisecondTotime(long milliSeconds) {

        String timerString = "";
        String secondString;
        int hour = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hour > 0) {
            timerString = hour + ":";
        }
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;


    }

    private void updateSeekbar() {
        if (mediaPlayer.isPlaying()) {
            //seekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);

        }
    }


    /*
               ------------ for Recording Process-------------------------
*/

    private void setRecoding_layout() {

        question_layout.setVisibility(View.GONE);
        TV_recording.setVisibility(View.VISIBLE);
        recoding_layout.setVisibility(View.VISIBLE);

        TV_recording.setBackgroundResource(R.drawable.mocktestbgbluee);
        TV_recording.setTextColor(Color.WHITE);

        TV_question.setBackgroundResource(R.drawable.mocktestbg_gray);
        TV_question.setTextColor(Color.parseColor("#696969"));

        rippleBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissions()) {
                    //Start Recording
                    startRecording();
                    //rippleBackground.startRippleAnimation();
                    // Change button image and set Recording state to false
                    // recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.record_btn_recording, null));
                    isRecording = true;
                }

            }
        });


    }


    private void startRecording() {


        TV_question.setEnabled(false);
        TV_recording.setEnabled(false);
        rippleBackground.setEnabled(false);
        rippleBackground.startRippleAnimation();


        //Start timer from 0
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                // TODO Auto-generated method stub
                String currentTime = timer.getText().toString();
                if (currentTime.equals("00:20")) //put time according to you
                {

                    if (isRecording) {
                        //Stop Recording
                        timer.stop();

                        mediaRecorderr.stop();

                        recoding_layout.setVisibility(View.GONE);
                        RecodingPlay_Layout.setVisibility(View.VISIBLE);
                        isRecording = false;
                    }
                    rippleBackground.startRippleAnimation();


                }
            }
        });
        startrecording();

    }

    private boolean checkPermissions() {
        //Check permission
        if (ActivityCompat.checkSelfPermission(this, recordPermission) == PackageManager.PERMISSION_GRANTED) {
            //Permission Granted
            return true;
        } else {
            //Permission not granted, ask for permission
            ActivityCompat.requestPermissions(this, new String[]{recordPermission}, PERMISSION_CODE);
            return false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRecording) {
            mediaPlayerr.stop();
        }
    }


    //------------------------------------------------------
    private void startrecording() {

        if (checkPermissions()) {


            pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                    + UUID.randomUUID().toString() + "_audio_record.3gp";

            setupMediaRecorder();
            try {
                mediaRecorderr.prepare();
                mediaRecorderr.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
            //playRecord.setEnabled(false);
            //Stopplay.setEnabled(false);
            Toast.makeText(this, "Recording....", Toast.LENGTH_SHORT).show();


        } else {
            requestPermission();
        }

    }

    private void playRecord() {


        VoiceReording_ppv.toggle();
        TV_question.setEnabled(false);
        TV_recording.setEnabled(false);
        VoiceReording_ppv.setEnabled(false);

        mediaPlayerr = new MediaPlayer();
        try {

            mediaPlayerr.setDataSource(pathSave);
            mediaPlayerr.prepare();


        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayerr.start();
        Toast.makeText(this, "Playing.......", Toast.LENGTH_SHORT).show();
        setAnswer_layout();

    }

    private boolean checkPermissionss() {
        //Check permission
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;


    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REquestPermissionCode);


    }

    private void setupMediaRecorder() {


        mediaRecorderr = new MediaRecorder();
        mediaRecorderr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorderr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorderr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorderr.setOutputFile(pathSave);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REquestPermissionCode: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissin Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permissin Deined", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }

    /*
    --------------------------------------------------------------=================================------------------------------------
    */
    private void setAnswer_layout() {
        TV_Answer.setVisibility(View.VISIBLE);
        TV_recording.setEnabled(false);


        TV_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayerr.isPlaying()) {
                    mediaPlayerr.stop();
                    setAnswer_layoutContent();
                    // mediaPlayerr.reset();
                    //  setRecoding_layout();

                    // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {

                    setAnswer_layoutContent();
                }


            }
        });


    }

    private void setAnswer_layoutContent() {
        RecodingPlay_Layout.setVisibility(View.GONE);
        answer_layout.setVisibility(View.VISIBLE);

        TV_recording.setBackgroundResource(R.drawable.mocktestbg_gray);
        TV_recording.setTextColor(Color.parseColor("#696969"));

        TV_Answer.setBackgroundResource(R.drawable.mocktestbgbluee);
        TV_Answer.setTextColor(Color.WHITE);
        StartAnswer();
    }


    private Runnable answerupdater = new Runnable() {
        @Override
        public void run() {
            updateanswerSeekbar();
            long currentDuration = answerMediaPlayer.getCurrentPosition();
            TV_AnswerTime.setText(milisecondTotime(currentDuration));
        }
    };

    @SuppressLint("SetTextI18n")
    private void prepareAnswerMedia() {


        try {
            answerMediaPlayer.setDataSource("http://misfitamigos.com/naticcl_englishwise/mock_test_data/hindi/answers/Aged_care/Aged_Care_Centre_Hindi_2.mp3");
            answerMediaPlayer.prepare();
            TV_AnswerTotalTime.setText("/" + milisecondTotime(answerMediaPlayer.getDuration()) + " Sec");
            Log.d("musicPlayerTimehere", String.valueOf(answerMediaPlayer.getDuration()));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateanswerSeekbar() {
        if (answerMediaPlayer.isPlaying()) {
            //seekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            answerhandler.postDelayed(answerupdater, 1000);

        }
    }

    private void StartAnswer() {
        TV_question.setEnabled(false);
        TV_Answer.setEnabled(false);
        prepareAnswerMedia();
        answer_play_pause_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (answerMediaPlayer.isPlaying()) {
                    answerhandler.removeCallbacks(answerupdater);
                    answerMediaPlayer.pause();
                    answer_play_pause_view.toggle();
                    answer_play_pause_view.isPlay();
                    // imageplayPause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                } else {
                    answerMediaPlayer.start();

                    //imageplayPause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                    answer_play_pause_view.toggle();
                    updateanswerSeekbar();
                }
            }
        });

        // playingQuestion();


    }

}

