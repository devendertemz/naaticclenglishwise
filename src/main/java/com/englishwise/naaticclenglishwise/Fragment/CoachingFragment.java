package com.englishwise.naaticclenglishwise.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;

public class CoachingFragment extends Fragment {
        TextView language;
   Customdialog customdialog;


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
     View view= inflater.inflate(R.layout.fragment_coaching, container, false);
        customdialog=new Customdialog(getContext());

        initView(view);
        customdialog.ShowLanguage();

     return  view;

    }

    private void initView(View view) {
        language=view.findViewById(R.id.language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}