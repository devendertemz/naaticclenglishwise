package com.englishwise.naaticclenglishwise.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.MockTestActivity;
import com.englishwise.naaticclenglishwise.Activity.VocabularyActivity;
import com.englishwise.naaticclenglishwise.Adapter.Vocabulary_Adapterr;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.trendyol.bubblescrollbarlib.BubbleScrollBar;
import com.trendyol.bubblescrollbarlib.BubbleTextProvider;

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

public class CoachingFragment extends Fragment {
    Vibrator vibe;
    RecyclerView Vocabulary_RV;
    BubbleScrollBar fastscroll;
    Vocabulary_Adapterr vocabulary_adapterrrrrr;
    List<VocabularyModel> mDataaaaaaaaa;
    LoadingDialogs loadingDialogs;

    public CoachingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coaching, container, false);

        initView(view);

        return view;

    }

    private void initView(View view) {

        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        loadingDialogs = new LoadingDialogs(getActivity());
        mDataaaaaaaaa= new ArrayList<>();
        Vocabulary_RV = view.findViewById(R.id.Vocabulary_RV);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        Vocabulary_RV.setLayoutManager(manager);

        fastscroll = view.findViewById(R.id.fastscroll);

        read_vocabulary();

    }
    private void read_vocabulary() {



        loadingDialogs.startLoadingDialogs();



        Call<ResponseBody> userlist = ApiClient.getUserService().read_vocabulary(SharedPrefManager.getInstans(getActivity().getApplicationContext()).getlanguage());

        userlist.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(getActivity(), response.code() + "", Toast.LENGTH_SHORT).show();
                String s = null;

                if (response.code() == 200) {
                    try {
                        s = response.body().string();
                        JSONObject jsonObject=new JSONObject(s);
                        String count=jsonObject.getString("count");
                        Toast.makeText(getActivity(), count+"", Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray=jsonObject.getJSONArray("records");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String english_word=jsonObject1.getString("english_word");
                            String translated_word=jsonObject1.getString("translated_word");
                            mDataaaaaaaaa.add(new VocabularyModel(english_word,translated_word));


                            vocabulary_adapterrrrrr = new Vocabulary_Adapterr(getContext(), mDataaaaaaaaa);
                            Vocabulary_RV.setAdapter(vocabulary_adapterrrrrr);

                            fastscroll.attachToRecyclerView(Vocabulary_RV);
                            fastscroll.getBubbleTextProvider();

                            fastscroll.setBubbleTextProvider(new BubbleTextProvider() {
                                @Override
                                public String provideBubbleText(int i) {
                                    //return "jhk";
                                    return vocabulary_adapterrrrrr.mData.get(i).getSuportedWord().substring(0,1);
                                    // return mDataaaaaaaaa.get(i).getEnglis().substring(0,1);

                                }
                            });


                        }

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


}