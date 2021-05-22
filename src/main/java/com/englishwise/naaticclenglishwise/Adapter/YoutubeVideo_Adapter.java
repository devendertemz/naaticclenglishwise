package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.englishwise.naaticclenglishwise.ModalResponse.BlogRespBean;
import com.englishwise.naaticclenglishwise.ModalResponse.VideoRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;

import java.util.ArrayList;

public class YoutubeVideo_Adapter extends RecyclerView.Adapter<YoutubeVideo_Adapter.myViewHolder> {


    ArrayList<VideoRespBean.Datum> modelArrayList;
    Context context;
    private ItemClickListenerr itemClickListenerr;


    public YoutubeVideo_Adapter(ArrayList<VideoRespBean.Datum> modelArrayList, Context context, ItemClickListenerr itemClickListenerr) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.itemClickListenerr = itemClickListenerr;
    }

    @NonNull
    @Override
    public YoutubeVideo_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_adapter, parent, false);

        return new YoutubeVideo_Adapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideo_Adapter.myViewHolder holder, int position) {

        Glide.with(context)
                .load(modelArrayList.get(position).getThumbnailImage())
                .into(holder.ivMediaCoverImage);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMediaCoverImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMediaCoverImage = itemView.findViewById(R.id.ivMediaCoverImage);
            ivMediaCoverImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListenerr.onClick(getAdapterPosition());

                }
            });

        }
    }
}
