package com.englishwise.naaticclenglishwise.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Adapter.MockTestList_Adapter;
import com.englishwise.naaticclenglishwise.Modal.GetMockDetailsResponseData;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.R;

import java.util.ArrayList;
import java.util.List;

public class SetTestFragment extends Fragment implements   MockTestList_Adapter.MockDetailsAdapterListener{

    ImageView AboutIV;
    RecyclerView RV_MockTest;
    private ArrayList<GetMockDetailsResponseData> GetMockDetailssList;
    private  MockTestList_Adapter mockTestList_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_set_test, container, false);
        initView(view);
        AboutNoice("Information about Mock test","A mock test is defined as an examination, which does not have any marks. The test will assist the student or the candidate to acquire an idea as to how it would look like in a real time frame. You can take a mock test on various subjects that include programming");

        return  view;

    }

    private void initView(View view) {
        GetMockDetailssList=new ArrayList<>();
        AboutIV=view.findViewById(R.id.AboutIV);
        RV_MockTest=view.findViewById(R.id.RV_MockTest);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        RV_MockTest.setLayoutManager(manager);
        for (int i=0;i<100;i++)
        {
            GetMockDetailssList.add(new GetMockDetailsResponseData("geg","rjrtjr"));
        }

        mockTestList_adapter = new MockTestList_Adapter(GetMockDetailssList,getContext());
        RV_MockTest.setAdapter(mockTestList_adapter);
        AboutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutNoice("Information about Mock test","A mock test is defined as an examination, which does not have any marks. The test will assist the student or the candidate to acquire an idea as to how it would look like in a real time frame. You can take a mock test on various subjects that include programming");
            }});





    }

    public void AboutNoice(String titlee,String msg) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.aboutnotice_layout, null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView cancel_Iv=view.findViewById(R.id.cancel_Iv);

        Button read_btn = view.findViewById(R.id.read_btn);
        TextView title= view.findViewById(R.id.title);

        TextView message= view.findViewById(R.id.message);
        title.setText(titlee);
        message.setText(msg);

        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    public void ResultViewOnClick(View v, int position) {
        Toast.makeText(getContext(), position+"ResultViewOnClick", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Dialouge1ViewOnClick(View v, int position) {
        Toast.makeText(getContext(), position+"ResultViewOnClick", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Dialouge2ViewOnClick(View v, int position) {
        Toast.makeText(getContext(), position+"ResultViewOnClick", Toast.LENGTH_SHORT).show();

    }
}