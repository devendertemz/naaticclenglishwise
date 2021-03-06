package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.englishwise.naaticclenglishwise.Modal.ScreenItem;
import com.englishwise.naaticclenglishwise.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class IntroViewPagerAdapter extends PagerAdapter {

   Context mContext ;
   List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        GifImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);

        TextView title = layoutScreen.findViewById(R.id.intro_title);

        //TextView intro_title2 = layoutScreen.findViewById(R.id.intro_title2);
        TextView description = layoutScreen.findViewById(R.id.intro_description);




        title.setText(mListScreen.get(position).getTitle());
        //intro_title2.setText(mListScreen.get(position).getTitle2());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;





    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}
