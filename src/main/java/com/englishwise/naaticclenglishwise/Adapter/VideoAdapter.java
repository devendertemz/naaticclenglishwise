package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.englishwise.naaticclenglishwise.Modal.videoModelList;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListener;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.NewsViewHolder>{


    Context mContext;
    List<videoModelList> mData ;
    List<videoModelList> mDataFiltered ;
    boolean isDark = false;
    ItemClickListener itemClickListener;

    public VideoAdapter(Context mContext, List<videoModelList> mData, ItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
        this.itemClickListener = itemClickListener;
    }

 /* public HomeVideoAdapter(Context mContext, List<videoModelList> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;

    }*/

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.video_adapter_layout,viewGroup,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {

        // bind data here

        // we apply animation to views here
        // first lets create an animation for user photo
       // newsViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));

        // lets create the animation for the whole card
        // first lets create a reference to it
        // you ca use the previous same animation like the following

        // but i want to use a different one so lets create it ..
      //  newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));




        newsViewHolder.imageset.setImageResource(mDataFiltered.get(position).getScreenImg());
            newsViewHolder.date.setText(mDataFiltered.get(position).getDate());
        newsViewHolder.videotopic.setText(mDataFiltered.get(position).getTitle());
        newsViewHolder.videotopic2.setText(mDataFiltered.get(position).getTitle2());


    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }
/*
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFiltered = mData ;

                }
                else {
                    List<NewsItem> lstFiltered = new ArrayList<>();
                    for (NewsItem row : mData) {

                        if (row.getTitle().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<NewsItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }*/

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageset;
        TextView date,videotopic,videotopic2;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageset = itemView.findViewById(R.id.ivMediaCoverImage);
            date = itemView.findViewById(R.id.date);
            videotopic = itemView.findViewById(R.id.videotopic);

            videotopic2 = itemView.findViewById(R.id.videotopic2);
            imageset.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

    }

}
