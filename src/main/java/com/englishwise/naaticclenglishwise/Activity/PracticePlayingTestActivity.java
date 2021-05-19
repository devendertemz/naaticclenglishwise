package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PracticePlayingTestActivity extends AppCompatActivity {
    TextView TV_question, TV_recording, TV_Answer;


    private String idd,compareid;
    TextView test_no;

    LoadingDialogs loadingDialogs;

    private  String id, mid, Question, QuestionText, Answer, AnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_playing_test);
        initView();

    }

    private void initView() {
        loadingDialogs = new LoadingDialogs(this);

        TV_question = findViewById(R.id.TV_question);
        TV_recording = findViewById(R.id.TV_recording);
        TV_Answer = findViewById(R.id.TV_Answer);
        test_no= findViewById(R.id.test_no);

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            if (getIntent() != null) {
                idd = getIntent().getExtras().getString("id");

                compareid = getIntent().getExtras().getString("compareid");
                Playgingquestion();
              //  Toast.makeText(this, compareid + "", Toast.LENGTH_SHORT).show();


            }

        } catch (Exception e) {

        }
    }

    public void OnBackpress(View view) {
        onBackPressed();
    }


    public void Playgingquestion() {

        loadingDialogs.startLoadingDialogs();
        Call<ResponseBody> bodyCall = ApiClient.getUserService().Read_practicetest(idd);
       // Toast.makeText(this, idd+"", Toast.LENGTH_SHORT).show();
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(PracticePlayingTestActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    try {
                        s = response.body().string();
                        Toast.makeText(PracticePlayingTestActivity.this, s+"", Toast.LENGTH_SHORT).show();

                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        id = jsonObject1.getString("id");
                        test_no.setText("Test Set "+id);
                        mid = jsonObject1.getString("mid");
                        Question = jsonObject1.getString("question");
                        QuestionText = jsonObject1.getString("question_text");
                        Answer = jsonObject1.getString("answer");
                        AnsText = jsonObject1.getString("answer_text");
                        idd = jsonObject1.getString("next_id");
                        loadingDialogs.dismissDialog();

                       // StartQuestion();


                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }

                }
                loadingDialogs.dismissDialog();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PracticePlayingTestActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
                loadingDialogs.dismissDialog();

            }
        });


    }

    public void ANSWER_Playing_OnClick(View view) {
    }

    public void RECORDING_OnClick(View view) {
    }
}