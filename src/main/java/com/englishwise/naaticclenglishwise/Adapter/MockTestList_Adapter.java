package com.englishwise.naaticclenglishwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;
import com.englishwise.naaticclenglishwise.Modal.GetMockDetailsResponseData;
import com.englishwise.naaticclenglishwise.R;

import java.util.List;

public class MockTestList_Adapter extends RecyclerView.Adapter<MockTestList_Adapter.MyViewHolder> {
    private List<GetMockDetailsResponseData> GetMockDetailssList;

    Context context;
    public MockDetailsAdapterListener onClickListener;

    public MockTestList_Adapter(List<GetMockDetailsResponseData> GetMockDetailssList, Context context) {
        this.GetMockDetailssList = GetMockDetailssList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mocklist_adapter_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       /* if (GetMockDetailssList.get(position).getImage() != null && !followersList.get(position).getImage().trim().equalsIgnoreCase("")) {
            Picasso.with(context)

                    .load(followersList.get(position).getImage())
                    .resize(200, 200).into(holder.rv_iv_follower_image);
        }
*/
        holder.Tv_Title.setText(GetMockDetailssList.get(position).getTestTitle());
        holder.Tv_Date.setText(GetMockDetailssList.get(position).getDate());
        Pie pie= AnyChart.pie();
        pie.noData();
        holder.ChartView.setChart(pie);

       /* holder.Iv_Dialogue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.Dialouge1ViewOnClick(v, position);

            }
        });
        holder.Iv_Dialogue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.Dialouge2ViewOnClick(v, position);

            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return GetMockDetailssList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Tv_Title, Tv_Date, Tv_Result;
        private AnyChartView ChartView;
        private ImageView Iv_Dialogue1, Iv_Dialogue2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv_Title = itemView.findViewById(R.id.Tv_Title);
            Tv_Date = itemView.findViewById(R.id.Tv_Date);
            Tv_Result = itemView.findViewById(R.id.Tv_Result);
            ChartView = itemView.findViewById(R.id.ChartView);
            Iv_Dialogue1 = itemView.findViewById(R.id.Iv_Dialogue1);
            Iv_Dialogue2 = itemView.findViewById(R.id.Iv_Dialogue2);

          /*  Tv_Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "Result", Toast.LENGTH_SHORT).show();
                    onClickListener.ResultViewOnClick(v, getAdapterPosition());

                }
            });*/

        }
    }

    public interface MockDetailsAdapterListener {

        void ResultViewOnClick(View v, int position);

        void Dialouge1ViewOnClick(View v, int position);

        void Dialouge2ViewOnClick(View v, int position);
    }
}
