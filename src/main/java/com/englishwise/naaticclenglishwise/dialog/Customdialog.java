package com.englishwise.naaticclenglishwise.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.englishwise.naaticclenglishwise.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Customdialog {

    Context context;

    public Customdialog(Context context) {

        this.context = context;
    }

    public void Sucess(String titlee, String dess) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customdialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button read_btn = view.findViewById(R.id.read_btn);
        TextView title = view.findViewById(R.id.title);

        TextView des = view.findViewById(R.id.message);

        title.setText(titlee);
        des.setText(dess);
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
               // ShowLanguage();
                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();
    }
    public void Show_Text( String dess) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.showtext, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button read_btn = view.findViewById(R.id.read_btn);

       ImageView cancel_Iv = view.findViewById(R.id.cancel_Iv);
        TextView des = view.findViewById(R.id.message);

        des.setText(dess);
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                // ShowLanguage();
                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });
        cancel_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                // ShowLanguage();
                // Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();

            }
        });


        alertDialog.show();
    }


    public void Mocktestdialog(String titlee, String dess, int image) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mocktestdialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                //.setCancelable(false)
                .create();

        // alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout mockLL = view.findViewById(R.id.mockLL);
      //  mockLL.setAlpha((float) 0.4);
        TextView title = view.findViewById(R.id.title);
        ImageView imageView = view.findViewById(R.id.image);
        TextView des = view.findViewById(R.id.description);

        title.setText(titlee);
        des.setText(dess);
        imageView.setImageResource(image);


        alertDialog.show();
    }

    public void ShowLanguage() {

        LayoutInflater inflater = LayoutInflater.from(context);
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.languagedialog, null);

        ImageView cancel_Iv;
        TextView Hindi_TV, Tamil_TV, Urdu_TV, Punjabi_TV, Malayalam_TV, Telugu_TV, Nepaese_TV, Gujarati_TV, Spanish_TV;

        cancel_Iv = view.findViewById(R.id.cancel_Iv);
        Hindi_TV = view.findViewById(R.id.Hindi_TV);
        Tamil_TV = view.findViewById(R.id.Tamil_TV);
        Urdu_TV = view.findViewById(R.id.Urdu_TV);
        Punjabi_TV = view.findViewById(R.id.Punjabi_TV);
        Malayalam_TV = view.findViewById(R.id.Malayalam_TV);
        Telugu_TV = view.findViewById(R.id.Telugu_TV);
        Nepaese_TV = view.findViewById(R.id.Nepaese_TV);
        Gujarati_TV = view.findViewById(R.id.Gujarati_TV);
        Spanish_TV = view.findViewById(R.id.Spanish_TV);

        cancel_Iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();

            }
        });
        Hindi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hindi_TV.setTypeface(Hindi_TV.getTypeface(), Typeface.BOLD);
                Hindi_TV.setTextColor(Color.parseColor("#ff0000"));

                Toast.makeText(context, Hindi_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Tamil_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tamil_TV.setTypeface(Hindi_TV.getTypeface(), Typeface.BOLD);
                Tamil_TV.setTextColor(Color.parseColor("#ff0000"));

                Toast.makeText(context, Tamil_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Urdu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Urdu_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Punjabi_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Punjabi_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Malayalam_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Malayalam_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Telugu_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Telugu_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Nepaese_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Nepaese_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        Gujarati_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Gujarati_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });


        Spanish_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, Spanish_TV.getText().toString() + "", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });
        /* AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();
*/


        dialog.setContentView(view);
        dialog.show();


       /* title.setText(titlee);
        des.setText(dess);*/

        // alertDialog.show();
    }
}
