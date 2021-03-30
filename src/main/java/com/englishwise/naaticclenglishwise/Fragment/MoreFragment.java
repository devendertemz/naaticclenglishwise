package com.englishwise.naaticclenglishwise.Fragment;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.BlogActivity;
import com.englishwise.naaticclenglishwise.Activity.CCL_TestActivity;
import com.englishwise.naaticclenglishwise.Activity.EbooksActivity;
import com.englishwise.naaticclenglishwise.Activity.EditProfile_Activity;
import com.englishwise.naaticclenglishwise.Activity.VideoActivity;
import com.englishwise.naaticclenglishwise.Activity.VocabularyActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;

public class MoreFragment extends Fragment {


    private LinearLayout Ebooks_LL;
    private LinearLayout CCl_Test_LL;
    private LinearLayout Share_LL;
    private LinearLayout Videos_LL;
    private LinearLayout Rate_App_LL;
    private LinearLayout Blog_LL;
    private LinearLayout vocabulary_LL;
    private LinearLayout edit_profile_LL;

    ImageView language_IV;


    Customdialog customdialog;


    public MoreFragment() {
        // Required empty public constructor
    }


    // Tyt
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        final Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        customdialog = new Customdialog(getContext());
        vocabulary_LL = view.findViewById(R.id.Vocabulary_LL);
         edit_profile_LL = view.findViewById(R.id.edit_profile_LL);
        language_IV = view.findViewById(R.id.language_IV);
        Ebooks_LL = view.findViewById(R.id.Ebooks_LL);
        CCl_Test_LL = view.findViewById(R.id.CCl_Test_LL);
        Share_LL = view.findViewById(R.id.Share_LL);
        Videos_LL = view.findViewById(R.id.Videos_LL);
        Rate_App_LL = view.findViewById(R.id.Rate_App_LL);
        Blog_LL = view.findViewById(R.id.Blog_LL);

        customdialog.Sucess("Change language", "Once you change the language,you will lost all the current data.Are you sure you want to change the language ?");

        Videos_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), VideoActivity.class);
                startActivity(in);
            }


        });

        Blog_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), BlogActivity.class);
                startActivity(in);
            }


        });

        language_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                customdialog.Sucess("Change language", "Once you change the language,you will lost all the current data.Are you sure you want to change the language ?");
            }


        });


        vocabulary_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), VocabularyActivity.class);
                startActivity(in);
                //  showdialog("name","check");
            }


        });
        Share_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Share();
                //  showdialog("name","check");
            }


        });
        CCl_Test_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), CCL_TestActivity.class);
                startActivity(in);
                //  showdialog("name","check");
            }


        });
        edit_profile_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), EditProfile_Activity.class);
                startActivity(in);

            }


        });
        Ebooks_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in = new Intent(getActivity(), EbooksActivity.class);
                startActivity(in);

            }


        });

        Rate_App_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                RatingBar();
            }


        });

    }

  /*  private void showdialog(String titlee,String dess) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.customdialog,null);
        Button read_btn=view.findViewById(R.id.read_btn);
        TextView title=view.findViewById(R.id.title);

        TextView des=view.findViewById(R.id.message);

       *//* title.setText(titlee);
        des.setText(dess);*//*
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });


        AlertDialog alertDialog=new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
        alertDialog.show();
    }*/

    private void Share() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sAux = "Hey,\n Its amazing install Naati CCL Englishwise App " + "\n Download " + getResources().getString(R.string.app_name) + "\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName() + "\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void RatingBar() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.rating_layout, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RatingBar rating = view.findViewById(R.id.rating);
        ImageView cancel_Iv = view.findViewById(R.id.cancel_Iv);

        Button read_btn = view.findViewById(R.id.read_btn);


        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + rating.getNumStars();
                String ratingg = "Rating :: " + rating.getRating();
                Toast.makeText(getContext(), totalStars + "\n" + ratingg, Toast.LENGTH_LONG).show();
                alertDialog.cancel();

                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });


        cancel_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();

                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });

        alertDialog.show();
    }

}