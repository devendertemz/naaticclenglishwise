package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.R;

import java.util.Collections;
import java.util.List;

public class Vocabulary_Adapterr extends RecyclerView.Adapter<Vocabulary_Adapterr.MyViewHolder> {

    Context mContext;
    public List<VocabularyModel> mData;

    public Vocabulary_Adapterr(Context mContext, List<VocabularyModel> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }


    @NonNull
    @Override
    public Vocabulary_Adapterr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.vocabulary_adapter_layout, parent, false);


        return new Vocabulary_Adapterr.MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Vocabulary_Adapterr.MyViewHolder holder, int position) {

        holder.EnglishWord_TV.setText(mData.get(position).getEnglis());
        holder.SupportedLanguage_TV.setText(mData.get(position).getSuportedWord());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView EnglishWord_TV, SupportedLanguage_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            SupportedLanguage_TV = itemView.findViewById(R.id.SupportedLanguage_TV);

            EnglishWord_TV = itemView.findViewById(R.id.EnglishWord_TV);


        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
