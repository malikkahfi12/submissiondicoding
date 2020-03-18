package com.malik.mysubmission4.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.malik.mysubmission4.DetailActivity;
import com.malik.mysubmission4.R;
import com.malik.mysubmission4.config.UrlAPI;
import com.malik.mysubmission4.db.dbmovie.AppDatabase;
import com.malik.mysubmission4.db.dbmovie.MovieFavorite;
import com.malik.mysubmission4.helper.CustomOnItemClickListener;
import com.malik.mysubmission4.interfaces.OnItemClickCallback;
import com.malik.mysubmission4.model.Detail;

import java.util.List;


public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MyViewHolder> {

    private List<MovieFavorite> movieFavorites;
    private Context context;
    AppDatabase db;

    public MovieFavoriteAdapter(List<MovieFavorite> movieFavorites, Context context) {
        this.movieFavorites = movieFavorites;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_favorite, parent, false);
        db = Room.databaseBuilder(context,
                AppDatabase.class, "moviefavorite").allowMainThreadQueries().build();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvTitle.setText(movieFavorites.get(position).getTitle());
        holder.tvDescription.setText(movieFavorites.get(position).getDescription());
        Glide.with(context)
                .load(UrlAPI.URL_POSTER + movieFavorites.get(position).getPoster())
                .into(holder.imgPoster);

        holder.deleteItems.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                MovieFavorite movieFavorite = new MovieFavorite();
                movieFavorite.setId(movieFavorites.get(position).getId());
                db.queryDAO().deleteMovie(movieFavorite);
                movieFavorites.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, context.getString(R.string.delete_toast), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.cvContainer.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Detail detail = new Detail();
                detail.setTitle(movieFavorites.get(position).getTitle());
                detail.setOverview(movieFavorites.get(position).getDescription());
                detail.setPosterPath(movieFavorites.get(position).getPoster());
                detail.setId(movieFavorites.get(position).getId());
                detail.setReleaseDate(movieFavorites.get(position).getReleasedate());
                detail.setVote_average(movieFavorites.get(position).getRating());
                detail.setPosition(position);
                Intent iDetailActivity = new Intent(context, DetailActivity.class);
                iDetailActivity.putExtra(DetailActivity.EXTRA_DETAIL, detail);
                context.startActivity( iDetailActivity);
            }
        }));

    }

    @Override
    public int getItemCount() {
        Log.e("getItemCount: ", String.valueOf(movieFavorites.size()));
        return movieFavorites.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView deleteItems;
        private CardView cvContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteItems = itemView.findViewById(R.id.delete_ic);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_desc);
            cvContainer = itemView.findViewById(R.id.cv_container);
        }
    }
}
