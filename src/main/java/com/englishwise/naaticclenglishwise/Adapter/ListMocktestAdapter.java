package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.englishwise.naaticclenglishwise.Modal.GetMockDetailsResponseData;
import com.englishwise.naaticclenglishwise.ModalResponse.MockRespBean;
import com.englishwise.naaticclenglishwise.R;

import java.util.List;

public class ListMocktestAdapter extends RecyclerView.Adapter<ListMocktestAdapter.MyViewHolder> {

    private List<MockRespBean> mockRespBeanList;
    private Context context;
    private ListMocktestAdapter.ClickedItem clickedItem;

    public ListMocktestAdapter(ListMocktestAdapter.ClickedItem clickedItem) {

        this.clickedItem = clickedItem;
    }

    public void setData(List<MockRespBean> userResponseList) {
        this.mockRespBeanList = userResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListMocktestAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.listmocktestadapter, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MockRespBean userResponse = mockRespBeanList.get(position);
        // Log.e("image",userResponse.getImage());

        // Log.e("image", url);

        holder.textView.setText(userResponse.getCategoryName());

        holder.mocktestlisner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mockRespBeanList.size();
    }

    public interface ClickedItem {
        public void ClickedUser(MockRespBean userResponse);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private LinearLayout mocktestlisner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.TV_title);
            mocktestlisner = (LinearLayout) itemView.findViewById(R.id.mocktestlisner);
        }
    }
}
