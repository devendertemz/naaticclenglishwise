package com.englishwise.naaticclenglishwise.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.VideoPlayerActivity;
import com.englishwise.naaticclenglishwise.Adapter.YoutubeVideo_Adapter;
import com.englishwise.naaticclenglishwise.Modal.Youtube_Model;
import com.englishwise.naaticclenglishwise.ModalResponse.VideoRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ItemClickListenerr {

    TextView Hindi_TV, Tamil_TV, Urdu_TV, Punjabi_TV, Malayalam_TV, Telugu_TV, Nepaese_TV, Gujarati_TV, Spanish_TV, Start_Practice;
    LinearLayout AllCourse_LL, PTEBook_LL, AllBranch_LL, Practice_LL, OnlineClasse_LL, Stories_LL;
    RecyclerView video_adapter_layout;

    LoadingDialogs loadingDialogs;
    public Activity activity;

    private ArrayList<VideoRespBean.Datum> data;
    YoutubeVideo_Adapter youtubeVideo_adapter;


    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        Start_Practice = view.findViewById(R.id.Start_Practice);
        Hindi_TV = view.findViewById(R.id.Hindi_TV);
        Tamil_TV = view.findViewById(R.id.Tamil_TV);
        Urdu_TV = view.findViewById(R.id.Urdu_TV);
        Punjabi_TV = view.findViewById(R.id.Punjabi_TV);
        Malayalam_TV = view.findViewById(R.id.Malayalam_TV);
        Telugu_TV = view.findViewById(R.id.Telugu_TV);
        Nepaese_TV = view.findViewById(R.id.Nepaese_TV);
        Gujarati_TV = view.findViewById(R.id.Gujarati_TV);
        Spanish_TV = view.findViewById(R.id.Spanish_TV);

        AllBranch_LL = view.findViewById(R.id.All_Branches);
        PTEBook_LL = view.findViewById(R.id.PTE_E_Book);
        AllCourse_LL = view.findViewById(R.id.All_Courses);


        Practice_LL = view.findViewById(R.id.Practice_LL);
        OnlineClasse_LL = view.findViewById(R.id.OnlineClasse_LL);
        Stories_LL = view.findViewById(R.id.Stories_LL);

        video_adapter_layout = view.findViewById(R.id.video_adapter_layout);
        loadingDialogs = new LoadingDialogs(getActivity());

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        video_adapter_layout.setLayoutManager(manager);
        SetLanguage();
        //getVideo();
        get_youtubeVideo();

        Start_Practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getContext(), "start practice", Toast.LENGTH_SHORT).show();
                SetTestFragment setTestFragment = new SetTestFragment();
                FragmentManager setTestFragmentmanager = getActivity().getSupportFragmentManager();
                FragmentTransaction setTestFragmentransaction = setTestFragmentmanager.beginTransaction();
                setTestFragmentransaction.replace(R.id.contentPanel, setTestFragment);
                setTestFragmentransaction.commit();
            }
        });


    }


    private void SetLanguage() {

        final Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        Hindi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                //  Toast.makeText(getContext(), Hindi_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Tamil_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                // Toast.makeText(getContext(), Tamil_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });

        Urdu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                // Toast.makeText(getContext(), Urdu_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Punjabi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                //  Toast.makeText(getContext(), Punjabi_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Malayalam_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                // Toast.makeText(getContext(), Malayalam_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Telugu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                //  Toast.makeText(getContext(), Telugu_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Nepaese_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                //  Toast.makeText(getContext(), Nepaese_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Gujarati_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                //  Toast.makeText(getContext(), Gujarati_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });
        Spanish_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
              /*  Intent in=new Intent(getActivity(), MockTestActivity.class);
                startActivity(in);*/
                // Toast.makeText(getContext(), Spanish_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
            }
        });


        AllCourse_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);

                Toast.makeText(getContext(), "All Course", Toast.LENGTH_SHORT).show();
            }
        });
        PTEBook_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "PTE Book", Toast.LENGTH_SHORT).show();
                vibe.vibrate(50);

            }
        });
        AllBranch_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "All Branch", Toast.LENGTH_SHORT).show();
                vibe.vibrate(50);

            }
        });
        Practice_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Practice", Toast.LENGTH_SHORT).show();
                vibe.vibrate(50);

            }
        });
        OnlineClasse_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "OnlineClasse", Toast.LENGTH_SHORT).show();
                vibe.vibrate(50);

            }
        });
        Stories_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Stories", Toast.LENGTH_SHORT).show();
                vibe.vibrate(50);

            }
        });

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


                        youtubeVideo_adapter = new YoutubeVideo_Adapter(data, activity.getApplicationContext(), HomeFragment.this::onClick);
                        video_adapter_layout.setItemAnimator(new DefaultItemAnimator());
                        video_adapter_layout.setAdapter(youtubeVideo_adapter);


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

        Intent intent=new Intent(getContext(), VideoPlayerActivity.class);
        intent.putExtra("youtube_model",youtube_model);
        startActivity(intent);
        //Toast.makeText(activity, data.get(position).getProfileVideoId()+"", Toast.LENGTH_SHORT).show();

    }
}