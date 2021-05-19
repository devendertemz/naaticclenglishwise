package com.englishwise.naaticclenglishwise.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.englishwise.naaticclenglishwise.Modal.Contactus;
import com.englishwise.naaticclenglishwise.R;
import com.englishwise.naaticclenglishwise.callback.ItemClickListenerr;

import java.util.List;

public class ContactUs_Adapter extends RecyclerView.Adapter<ContactUs_Adapter.MovieVH> {

    private static final String TAG = "MovieAdapter";
    List<Contactus> movieList;
    ItemClickListenerr itemClickListener;

    public ContactUs_Adapter(List<Contactus> movieList, ItemClickListenerr itemClickListener) {
        this.movieList = movieList;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactus_layout, parent, false);
        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {


        Contactus movie = movieList.get(position);

        Log.e(TAG, movie.getTitle());

        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getYear());
        holder.ratingTextView.setText(movie.getRating());
        holder.plotTextView.setText(movie.getPlot());

        boolean isExpanded = movieList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, yearTextView, ratingTextView, plotTextView;
        RelativeLayout relative_layout;


        public MovieVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            plotTextView = itemView.findViewById(R.id.plotTextView);
            relative_layout = itemView.findViewById(R.id.relative_layout);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            ratingTextView.setOnClickListener(this);
            relative_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Contactus movie = movieList.get(getAdapterPosition());
                    movie.setExpanded(!movie.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(getAdapterPosition());
        }
    }


}