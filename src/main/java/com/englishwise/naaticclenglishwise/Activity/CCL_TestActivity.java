package com.englishwise.naaticclenglishwise.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.LinearLayout;

import com.englishwise.naaticclenglishwise.Fragment.TestFragment;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.dialog.Customdialog;
import com.englishwise.naaticclenglishwise.util.util;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CCL_TestActivity extends AppCompatActivity {


    TabLayout tabLayout;

    LinearLayout CCl_Test_LL;

    ViewPager viewPager;
    Vibrator vibe;
    Customdialog customdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_c_l__test);
        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        CCl_Test_LL=findViewById(R.id.CCl_Test_LL);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Test Format");
        arrayList.add("Test Marking");
        arrayList.add("How to Apply");
        tabLayout.setupWithViewPager(viewPager);
        prepareViewPager(viewPager, arrayList);
        customdialog=new Customdialog(this);
        CCl_Test_LL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                customdialog.Mocktestdialog("Answer button","Click On the answer button to listen to prerecorded answer.",R.drawable.stories);
            }
        });

    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adater = new MainAdapter(getSupportFragmentManager());

        TestFragment testFragment = new TestFragment();
        for (int i = 0; i < arrayList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("title", arrayList.get(i));
            testFragment.setArguments(bundle);
            adater.addFragment(testFragment, arrayList.get(i));
            testFragment = new TestFragment();

        }
        viewPager.setAdapter(adater);

    }

    public void OnBackpress(View view) {
        vibe.vibrate(50);
        onBackPressed();
    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3};

        public void addFragment(Fragment fragment, String s) {
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);

        }

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {


            return fragmentArrayList.get(position);

        }

        @Override
        public int getCount() {

            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            //return super.getPageTitle(position);
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), imageList[position]);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString("" + stringArrayList.get(position));
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;

        }
    }
}