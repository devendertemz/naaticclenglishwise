package com.englishwise.naaticclenglishwise.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.Practicetest_Activity;
import com.englishwise.naaticclenglishwise.Activity.VocabularyActivity;
import com.englishwise.naaticclenglishwise.Adapter.ListMocktestAdapter;
import com.englishwise.naaticclenglishwise.Adapter.MockTestList_Adapter;
import com.englishwise.naaticclenglishwise.Adapter.Vocabulary_Adapterr;
import com.englishwise.naaticclenglishwise.Modal.GetMockDetailsResponseData;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.ModalResponse.MockRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.Rtrofit.ApiClient;
import com.englishwise.naaticclenglishwise.dialog.LoadingDialogs;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
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

public class SetTestFragment extends Fragment implements ListMocktestAdapter.ClickedItem {
    LoadingDialogs loadingDialogs;
    ImageView AboutIV;
    RecyclerView RV_MockTest;
    private ArrayList<MockRespBean> GetMockDetailssList;
    private ListMocktestAdapter listMocktestAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_set_test, container, false);
        initView(view);

        return view;

    }

    private void initView(View view) {
        loadingDialogs = new LoadingDialogs(getActivity());

        GetMockDetailssList = new ArrayList<>();
        //AboutIV=view.findViewById(R.id.AboutIV);
        RV_MockTest = view.findViewById(R.id.RV_MockTest);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        RV_MockTest.setLayoutManager(manager);
        ReadMocktestList();

        listMocktestAdapter = new ListMocktestAdapter(this::ClickedUser);

       /* listMocktestAdapter = new ListMocktestAdapter(GetMockDetailssList,getContext());
        RV_MockTest.setAdapter(listMocktestAdapter);*/
       /* AboutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutNoice("Information about Mock test","A mock test is defined as an examination, which does not have any marks. The test will assist the student or the candidate to acquire an idea as to how it would look like in a real time frame. You can take a mock test on various subjects that include programming");
            }});
*/


    }

    private void ReadMocktestList() {
        loadingDialogs.startLoadingDialogs();

        Call<List<MockRespBean>> userlist = ApiClient.getUserService().Read_MocktestList(SharedPrefManager.getInstans(getContext()).getlanguage());

        userlist.enqueue(new Callback<List<MockRespBean>>() {
            @Override
            public void onResponse(Call<List<MockRespBean>> call, Response<List<MockRespBean>> response) {


                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    List<MockRespBean> userResponses = response.body();
                    listMocktestAdapter.setData(userResponses);
                    RV_MockTest.setAdapter(listMocktestAdapter);
                    loadingDialogs.dismissDialog();

                } else {
                    loadingDialogs.dismissDialog();

                    try {
                        String errorRes = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
           /*     if (response.isSuccessful()) {
                    List<ProductDeatilsResponse> userResponses = response.body();
                    for (int i=0;i<userResponses.size();i++)
                    {
                        name.setText(  userResponses.get(i).getProductName());
                        finalPrice.setText("Rs."+userResponses.get(i).getActualAmount());
                        discountprice.setText("Rs."+userResponses.get(i).getPrice());
                        discount.setText(userResponses.get(i).getDiscount()+"% OFF");
                        product_desc.setText(HtmlCompat.fromHtml(userResponses.get(i).getProductDesc(), 0));

                        String url = "http://api.ourprive.com/" + userResponses.get(i).getImages();

                        Picasso.get().load(url).into(d_image);

                        Toast.makeText(ProdcutDeatails.this, userResponses.get(i).getCategoryname(), Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(ProdcutDeatails.this, "right way", Toast.LENGTH_SHORT).show();


                  *//*  secondcategory_adapterr.setData(userResponses);
                    recyclerView.setAdapter(secondcategory_adapterr);*//*

                }*/

            }

            @Override
            public void onFailure(Call<List<MockRespBean>> call, Throwable t) {
                loadingDialogs.dismissDialog();

                Log.e("failure", t.getLocalizedMessage());
                // loadingDialogs.dismissDialog();
            }
        });

    }

    @Override
    public void ClickedUser(MockRespBean userResponse) {

        Intent in=new Intent(getContext(), Practicetest_Activity.class);
        in.putExtra("id",userResponse.getDid());
        in.putExtra("CategoryName",userResponse.getCategoryName());
        startActivity(in);


     //  Toast.makeText(getContext(), userResponse.getCategoryName()+userResponse.getDid()+"", Toast.LENGTH_SHORT).show();

    }
}