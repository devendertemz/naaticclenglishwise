package com.englishwise.naaticclenglishwise.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.EbooksActivity;
import com.englishwise.naaticclenglishwise.Activity.EditProfile_Activity;
import com.englishwise.naaticclenglishwise.Activity.VocabularyActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;

public class MoreFragment extends Fragment {
    LinearLayout Vocabulary_LL,edit_profile_LL,Ebooks_LL;
    ImageView language_IV;


    Customdialog customdialog;


    public MoreFragment() {
        // Required empty public constructor
    }


    // T
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view= inflater.inflate(R.layout.fragment_profile, container, false);

      initView(view);

      return  view;
    }

    private void initView(View view) {
        final Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        Vocabulary_LL=view.findViewById(R.id.Vocabulary_LL);
        edit_profile_LL=view.findViewById(R.id.edit_profile_LL);
        language_IV=view.findViewById(R.id.language_IV);
        Ebooks_LL=view.findViewById(R.id.Ebooks_LL);
        customdialog=new Customdialog(getContext());
        customdialog.Sucess("Change language","Once you change the language,you will lost all the current data.Are you sure you want to change the language ?");


        language_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                customdialog.Sucess("Change language","Once you change the language,you will lost all the current data.Are you sure you want to change the language ?");
            }


        });


        Vocabulary_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in=new Intent(getActivity(), VocabularyActivity.class);
                startActivity(in);
              //  showdialog("name","check");
            }


        });

        edit_profile_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in=new Intent(getActivity(), EditProfile_Activity.class);
                startActivity(in);

            }


        });
        Ebooks_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
                Intent in=new Intent(getActivity(), EbooksActivity.class);
                startActivity(in);

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

}