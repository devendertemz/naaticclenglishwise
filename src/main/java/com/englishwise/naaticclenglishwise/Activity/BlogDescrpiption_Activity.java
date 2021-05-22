package com.englishwise.naaticclenglishwise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.englishwise.naaticclenglishwise.BuildConfig;
import com.englishwise.naaticclenglishwise.Modal.BlogModel;
import com.englishwise.naaticclenglishwise.R;

public class BlogDescrpiption_Activity extends AppCompatActivity {
    public BlogModel blogModel;
    ImageView prodImage;
    TextView title,Shortdescrption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_descrpiption_);
        Shortdescrption=findViewById(R.id.Shortdescrption);
        title=findViewById(R.id.title);
        prodImage=findViewById(R.id.prodImage);

        blogModel = (BlogModel) getIntent().getSerializableExtra("blogModel");
        Glide.with(getApplicationContext())
                .load(blogModel.image)
                .into(prodImage);
        title.setText(blogModel.title);
        Shortdescrption.setText(blogModel.longDescription);


      //  Toast.makeText(this, blogModel.title+"", Toast.LENGTH_SHORT).show();

    }

    public void OnBackpress(View view) {
        onBackPressed();
    }
    
    public void SharingBlog(View view) {


        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                blogModel.title+"\n"+blogModel.longDescription);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }
}