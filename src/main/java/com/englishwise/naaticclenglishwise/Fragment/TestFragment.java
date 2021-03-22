package com.englishwise.naaticclenglishwise.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.englishwise.naaticclenglishwise.R;

public class TestFragment extends Fragment {
    TextView test;
    public TestFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_test, container, false);
         test=view.findViewById(R.id.test);

         test.setText(getArguments().getString("title"));

        return  view;
    }
}