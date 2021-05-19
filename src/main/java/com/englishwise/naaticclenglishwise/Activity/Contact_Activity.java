package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Adapter.ContactUs_Adapter;
import com.englishwise.naaticclenglishwise.Modal.Contactus;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;

import java.util.ArrayList;
import java.util.List;

public class Contact_Activity extends AppCompatActivity implements ItemClickListenerr {
    RecyclerView recyclerView;

    List<Contactus> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_contact_);

        recyclerView = findViewById(R.id.recyclerVieww);
      /*  GridLayoutManager manager = new GridLayoutManager(this, 1,GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
*/
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {

    }

    private void initData() {


        movieList = new ArrayList<>();

        movieList.add(new Contactus("Sydney Parramatta", "02-8628-7293", "info@englishwise.com.au", "Ground floor, 43 Hunter St Parramatta 2150"));
        movieList.add(new Contactus("Sydney CBD", " 02-8628-7293", "info@englishwise.com.au", "Level 4, 245 Castleragh Street, Sydney NSW 2000"));

        movieList.add(new Contactus("Brisbane", " 07-3108-5871", "info@englishwise.com.au", "Level 1, 74-76 McLachlan Street, Fortitude Valley, Brisbane 4006"));
        movieList.add(new Contactus("Melbourne", "0424 341 310", "info@englishwise.com.au", "Level 1/318 King St, Melbourne VIC 3000"));
        movieList.add(new Contactus("Canberra", "0404 688 318", " info@englishwise.com.au", "Unit 6, 23-25 Lathlain Street, Belconnen, Canberra, 2617"));

        movieList.add(new Contactus("Tasmania", "03 6200 0862 ", " info@englishwise.com.au", "21 Bathurst Street, Hobart, Tasmania, 7000"));

        movieList.add(new Contactus("Darwin", " 0420 332 609", " info@englishwise.com.au", "5/90 Mitchell Street, Darwin NT, 0800"));
        movieList.add(new Contactus("Toowoomba", " 0420 332 609", " info@englishwise.com.au", "Level1/241 Margaret St, Toowoomba City QLD 4350"));
        movieList.add(new Contactus("Adelaide", " 0420 332 609", " info@englishwise.com.au", "Level 1, 72 Currie Street, Adelaide CBD, South Australia, 5000"));

        movieList.add(new Contactus("Oakleigh", " 0420 332 609", " info@englishwise.com.au", "13 Chester St, Oakleigh VIC 3166, Australia"));
        movieList.add(new Contactus("Gold Coast", " 0420 332 609", " info@englishwise.com.au", "113 Scarborough St, Southport QLD 4215, Australia."));

        movieList.add(new Contactus("Perth", " 0420 332 609", " info@englishwise.com.au", "Suite 5/9 Victoria Avenue Perth 6000"));

        ContactUs_Adapter contactUs_adapter = new ContactUs_Adapter(movieList,Contact_Activity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactUs_adapter);
    }

    public void OnBackpress(View view) {
        onBackPressed();
    }

    @Override
    public void onClick(int position) {



        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + Uri.encode(movieList.get(position).getRating().trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);

     /*   String uri = "tel:" + movieList.get(position).getRating().trim() ;
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);*/
       // Toast.makeText(this, movieList.get(position).getRating()+"", Toast.LENGTH_SHORT).show();

    }
}