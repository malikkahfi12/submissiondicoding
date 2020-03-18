package com.malik.mysubmission4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.malik.mysubmission4.DetailActivity;
import com.malik.mysubmission4.DetailSerialActivity;
import com.malik.mysubmission4.R;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.helper.CustomOnItemClickListener;
import com.malik.mysubmission4.interfaces.OnItemClickCallback;
import com.malik.mysubmission4.model.Detail;
import com.malik.mysubmission4.pojo.Serial.ResultsItem;

import java.util.List;

public class SerialAdapter extends RecyclerView.Adapter<SerialAdapter.MyViewHolder> {

    private Context context;
    private List<ResultsItem> resultsItems;

    public SerialAdapter(Context context, List<ResultsItem> resultsItems) {
        this.context = context;
        this.resultsItems = resultsItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (resultsItems.get(position).getPosterPath() != null){
            Glide.with(context).load(UrlAPI.URL_POSTER + resultsItems.get(position).getPosterPath())
                    .into(holder.imgPoster);
        }
        holder.tvTitle.setText(resultsItems.get(position).getName());
        if (resultsItems.get(position).getOverview().equals("")){
            holder.tvDescription.setText(R.string.lorem_ipsum);
        } else {
            holder.tvDescription.setText(resultsItems.get(position).getOverview());
        }
        holder.cvContainer.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Detail detail = new Detail();
                detail.setId(resultsItems.get(position).getId());
                detail.setOverview(resultsItems.get(position).getOverview());
                detail.setPosterPath(resultsItems.get(position).getPosterPath());
                detail.setReleaseDate(resultsItems.get(position).getFirstAirDate());
                detail.setTitle(resultsItems.get(position).getName());
                detail.setVote_average(resultsItems.get(position).getVoteAverage());

                Intent iDetailActivity = new Intent(context, DetailSerialActivity.class);
                iDetailActivity.putExtra(DetailActivity.EXTRA_DETAIL, detail);
                context.startActivity(iDetailActivity);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cvContainer;
        private ImageView imgPoster;
        private TextView tvTitle;
        private TextView tvDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvContainer = itemView.findViewById(R.id.cv_container);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_desc);
        }
    }
}
