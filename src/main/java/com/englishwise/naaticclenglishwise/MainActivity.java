package com.englishwise.naaticclenglishwise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.englishwise.naaticclenglishwise.Fragment.CoachingFragment;
import com.englishwise.naaticclenglishwise.Fragment.HomeFragment;
import com.englishwise.naaticclenglishwise.Fragment.MoreFragment;
import com.englishwise.naaticclenglishwise.Fragment.SetTestFragment;
import com.englishwise.naaticclenglishwise.dialog.AppUpdateChecker;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;
import com.englishwise.naaticclenglishwise.util.util;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.irozon.sneaker.Sneaker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    private final static int ID_HOME = 1;
    private final static int ID_TESTSET = 2;
    private final static int ID_COACHING = 3;
    private final static int ID_MORE = 4;
    MeowBottomNavigation bottomNavigation;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);*/
        setContentView(R.layout.activity_main);


        util.blackiteamstatusbar(this, R.color.gradient_end_color);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        bottomNavigation = findViewById(R.id.bottomNavigation);
        MeowBottomNavigation();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            //Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
            Sneaker.with(this)
                    .setTitle("Hi ! \n "+SharedPrefManager.getInstans(getApplicationContext()).getfullname()+" \n Good Morning ")
                    .setMessage("")
                    .sneakSuccess();
        }else if(timeOfDay >= 12 && timeOfDay < 16){

            Sneaker.with(this)
                    .setTitle("Hi ! \n "+SharedPrefManager.getInstans(getApplicationContext()).getfullname()+" \n Good Afternoon ")
                    .setMessage("")
                    .sneakSuccess();

           // Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
        }else if(timeOfDay >= 16 && timeOfDay < 21){
           // Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
            Sneaker.with(this)
                    .setTitle("Hi ! \n "+SharedPrefManager.getInstans(getApplicationContext()).getfullname()+" \n Good Evening ")
                    .setMessage("")
                    .sneakSuccess();

        }else if(timeOfDay >= 21 && timeOfDay < 24){


            Sneaker.with(this)
                    .setTitle("Hi ! \n "+SharedPrefManager.getInstans(getApplicationContext()).getfullname()+" \n Good Night ")
                    .setMessage("")
                    .sneakSuccess();
           // Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
        }



        Log.e("user",        SharedPrefManager.getInstans(getApplicationContext()).getNumber()+
                SharedPrefManager.getInstans(getApplicationContext()).getemail()+
                SharedPrefManager.getInstans(getApplicationContext()).getfullname()+
                SharedPrefManager.getInstans(getApplicationContext()).getlanguage()+
                SharedPrefManager.getInstans(getApplicationContext()).getprofileImage()+
                SharedPrefManager.getInstans(getApplicationContext()).getUserId()


        );

        AppUpdateChecker appUpdateChecker=new AppUpdateChecker(this);  //pass the activity in constructure
        appUpdateChecker.checkForUpdate(false); //mannual check false here

    }

    public void MeowBottomNavigation() {

        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_TESTSET, R.drawable.ic_testset));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_COACHING, R.drawable.ic_coaching));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_MORE, R.drawable.ic_more));


        //  bottomNavigation.setCount(ID_HOME, "115");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                id = item.getId();
             //   Toast.makeText(MainActivity.this, "clicked item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
               // Toast.makeText(MainActivity.this, "showing item : " + item.getId(), Toast.LENGTH_SHORT).show();

                String name;
                switch (item.getId()) {
                    case ID_HOME:

                      //  vibe.vibrate(50);

                        HomeFragment HomeFragment = new HomeFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.contentPanel, HomeFragment);
                        transaction.commit();
                        break;

                    case ID_TESTSET:
                        vibe.vibrate(50);

                        SetTestFragment setTestFragment = new SetTestFragment();
                        FragmentManager setTestFragmentmanager = getSupportFragmentManager();
                        FragmentTransaction setTestFragmentransaction = setTestFragmentmanager.beginTransaction();
                        setTestFragmentransaction.replace(R.id.contentPanel, setTestFragment);
                        setTestFragmentransaction.commit();
                        break;
                    case ID_COACHING:
                        vibe.vibrate(50);

                        CoachingFragment coachingFragment = new CoachingFragment();
                        FragmentManager coachingFragmentmanager = getSupportFragmentManager();
                        FragmentTransaction coachingFragmenttransaction = coachingFragmentmanager.beginTransaction();
                        coachingFragmenttransaction.replace(R.id.contentPanel, coachingFragment);
                        coachingFragmenttransaction.commit();
                        break;
                    case ID_MORE:

                        vibe.vibrate(50);

                        MoreFragment profileFragment = new MoreFragment();
                        FragmentManager profilemanager = getSupportFragmentManager();
                        FragmentTransaction profiletransaction = profilemanager.beginTransaction();
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
               // Toast.makeText(MainActivity.this, "reselected item : " + item.getId(), Toast.LENGTH_SHORT).show();
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


        if (id == 0) {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        } else {
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