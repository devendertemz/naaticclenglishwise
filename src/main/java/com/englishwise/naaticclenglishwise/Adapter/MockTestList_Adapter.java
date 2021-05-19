package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.englishwise.naaticclenglishwise.Modal.GetMockDetailsResponseData;
import com.englishwise.naaticclenglishwise.Modal.VocabularyModel;
import com.englishwise.naaticclenglishwise.ModalResponse.MockRespBean;
import com.englishwise.naaticclenglishwise.ModalResponse.PracticetestRepo;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListener;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;

import java.util.List;

public class MockTestList_Adapter extends RecyclerView.Adapter<MockTestList_Adapter.MyViewHolder> {


    Context mContext;
    public List<PracticetestRepo> mData;
    ItemClickListenerr itemClickListener;

    public MockTestList_Adapter(Context mContext, List<PracticetestRepo> mData, ItemClickListenerr itemClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MockTestList_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.mocklist_adapter_layout, parent, false);
        return new MockTestList_Adapter.MyViewHolder(layout);


    }

    @Override
    public void onBindViewHolder(@NonNull MockTestList_Adapter.MyViewHolder holder, int position) {
        holder.Tv_Title.setText("Test Set-" + mData.get(position).getId());
        holder.Tv_Date.setText("Updated On:" + mData.get(position).getCreateAt());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Tv_Title, Tv_Date, Tv_Result;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv_Title = itemView.findViewById(R.id.Tv_Title);
            Tv_Date = itemView.findViewById(R.id.Tv_Date);
            Tv_Result = itemView.findViewById(R.id.Tv_Result);
            Tv_Result.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(getAdapterPosition());
        }
    }
}
