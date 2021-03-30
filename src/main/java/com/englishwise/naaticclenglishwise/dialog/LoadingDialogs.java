package com.englishwise.naaticclenglishwise.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.englishwise.naaticclenglishwise.R;


public class LoadingDialogs {

   private Activity activity;
    private AlertDialog dialog;

    public LoadingDialogs
            (Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialogs()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.customloading,null));
        builder.setCancelable(false);
        dialog=builder.create();
        dialog.show();
    }



    public  void dismissDialog()
    {
        dialog.dismiss();
    }
}
