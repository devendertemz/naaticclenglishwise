package com.englishwise.naaticclenglishwise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Activity.ProfileActivity;
import com.englishwise.naaticclenglishwise.Fragment.HomeFragment;
import com.englishwise.naaticclenglishwise.Fragment.ProfileFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class MainActivity extends AppCompatActivity {


    private final static int ID_HOME = 1;
    private final static int ID_MESSAGE = 2;
    private final static int ID_NOTIFICATION = 3;
    private final static int ID_ACCOUNT = 4;
    MeowBottomNavigation bottomNavigation;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


       bottomNavigation = findViewById(R.id.bottomNavigation);
        MeowBottomNavigation();


    }

    public void MeowBottomNavigation() {

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MESSAGE, R.drawable.ic_message));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_NOTIFICATION, R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.ic_account));


        //  bottomNavigation.setCount(ID_HOME, "115");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                id=item.getId();
                Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;
                switch (item.getId()) {
                    case ID_HOME:


                 HomeFragment HomeFragment = new HomeFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.contentPanel, HomeFragment);
                        transaction.commit();
                        break;

                    case ID_MESSAGE:
                        name = "MESSAGE";
                        break;
                    case ID_NOTIFICATION:
                        name = "NOTIFICATION";
                        break;
                    case ID_ACCOUNT:


                        ProfileFragment profileFragment = new ProfileFragment();
                        FragmentManager profilemanager = getSupportFragmentManager();
                        FragmentTransaction  profiletransaction = profilemanager.beginTransaction();
                        profiletransaction.replace(R.id.contentPanel, profileFragment);
                        profiletransaction.commit();
                        break;
                    default:
                        name = "";
                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

//        bottomNavigation.setCount(ID_HOME, "115");

        bottomNavigation.show(ID_HOME, true);
        HomeFragment appNewsHome1Fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contentPanel, appNewsHome1Fragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection

        new CheckInternetConnection(this).checkConnection();
    }

    @Override
    public void onBackPressed() {

        /*
         */


        if (id==0){

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        else {
            HomeFragment appNewsHome1Fragment = new HomeFragment();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.contentPanel, appNewsHome1Fragment);
            transaction.commit();
            bottomNavigation.show(ID_HOME, true);
        }
        //super.onBackPressed();

    }
}