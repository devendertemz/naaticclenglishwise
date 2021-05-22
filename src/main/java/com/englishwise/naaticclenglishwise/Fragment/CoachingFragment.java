package com.englishwise.naaticclenglishwise.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.englishwise.naaticclenglishwise.Activity.MockTestActivity;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;

public class CoachingFragment extends Fragment {


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

    }
}