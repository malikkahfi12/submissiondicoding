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
import com.malik.mysubmission4.R;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.helper.CustomOnItemClickListener;
import com.malik.mysubmission4.interfaces.OnItemClickCallback;
import com.malik.mysubmission4.model.Detail;
import com.malik.mysubmission4.pojo.Movie.ResultsItem;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private Context context;
    private List<ResultsItem> resultsItemList;

    public MovieAdapter(Context context, List<ResultsItem> resultsItemList) {
        this.context = context;
        this.resultsItemList = resultsItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(UrlAPI.URL_POSTER + resultsItemList.get(position).getPosterPath()).into(holder.imgPoster);
        holder.tvTitle.setText(resultsItemList.get(position).getTitle());
        if (resultsItemList.get(position).getOverview().equals("")){
            holder.tvDescription.setText(R.string.lorem_ipsum);
        } else {
            holder.tvDescription.setText(resultsItemList.get(position).getOverview());
        }
        holder.cvContainer.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Detail detail = new Detail();
                detail.setTitle(resultsItemList.get(position).getTitle());
                detail.setOverview(resultsItemList.get(position).getOverview());
                detail.setPosterPath(resultsItemList.get(position).getPosterPath());
                detail.setId(resultsItemList.get(position).getId());
                detail.setReleaseDate(resultsItemList.get(position).getReleaseDate());
                detail.setVote_average(resultsItemList.get(position).getVoteAverage());
                detail.setPosition(position);
                Intent iDetailActivity = new Intent(context, DetailActivity.class);
                iDetailActivity.putExtra(DetailActivity.EXTRA_DETAIL, detail);
                context.startActivity( iDetailActivity);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return resultsItemList.size();
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