package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.englishwise.naaticclenglishwise.ModalResponse.BlogRespBean;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;
import com.englishwise.naaticclenglishwise.storage.SharedPrefManager;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.myViewHolder> {

    ArrayList<BlogRespBean.Datum> modelArrayList;
    Context context;
    private ItemClickListenerr itemClickListenerr;

    public BlogAdapter(ArrayList<BlogRespBean.Datum> modelArrayList, Context context, ItemClickListenerr itemClickListenerr) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.itemClickListenerr = itemClickListenerr;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_adapter_layout,parent,false);

        return new BlogAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.title.setText(modelArrayList.get(position).getTitle());
        holder.Shortdescrption.setText(modelArrayList.get(position).getShortDescription());

        Glide.with(context)
                .load(modelArrayList.get(position).getImage())
                .into(holder.prodImage);


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView prodImage;
        TextView title,Shortdescrption;
        LinearLayout Readmore;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImage=itemView.findViewById(R.id.prodImage);
            title=itemView.findViewById(R.id.title);
            Shortdescrption=itemView.findViewById(R.id.Shortdescrption);
            Readmore=itemView.findViewById(R.id.Readmore);
            Readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListenerr.onClick(getAdapterPosition());
                }
            });

        }
    }
}
