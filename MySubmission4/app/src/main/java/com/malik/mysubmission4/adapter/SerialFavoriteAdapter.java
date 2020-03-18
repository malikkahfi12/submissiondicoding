package com.malik.mysubmission4.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.malik.mysubmission4.db.dbserial.AppSerialDatabase;
import com.malik.mysubmission4.db.dbserial.SerialFavorite;
import com.malik.mysubmission4.helper.CustomOnItemClickListener;
import com.malik.mysubmission4.interfaces.OnItemClickCallback;
import com.malik.mysubmission4.model.Detail;

import java.util.List;

public class SerialFavoriteAdapter extends RecyclerView.Adapter<SerialFavoriteAdapter.MyViewHolder> {

    private List<SerialFavorite> serialFavorites;
    private Context context;
    private AppSerialDatabase db;
    public SerialFavoriteAdapter(List<SerialFavorite> serialFavorites, Context context) {
        this.serialFavorites = serialFavorites;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_favorite, parent, false);
        db = Room.databaseBuilder(context,
                AppSerialDatabase.class, "serialfavorite").allowMainThreadQueries().build();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(serialFavorites.get(position).getTitle());
        holder.tvDescription.setText(serialFavorites.get(position).getDescription());
        Glide.with(context)
                .load(UrlAPI.URL_POSTER + serialFavorites.get(position).getPoster())
                .into(holder.imgPoster);

        holder.deleteItems.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                SerialFavorite serialFavorite = new SerialFavorite();
                serialFavorite.setId(serialFavorites.get(position).getId());
                db.querySerialDAO().deleteMovie(serialFavorite);
                serialFavorites.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, context.getString(R.string.delete_toast), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.cvContainer.setOnClickListener(new CustomOnItemClickListener(position, new OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Detail detail = new Detail();
                detail.setTitle(serialFavorites.get(position).getTitle());
                detail.setOverview(serialFavorites.get(position).getDescription());
                detail.setPosterPath(serialFavorites.get(position).getPoster());
                detail.setId(serialFavorites.get(position).getId());
                detail.setReleaseDate(serialFavorites.get(position).getReleasedate());
                detail.setVote_average(serialFavorites.get(position).getRating());
                detail.setPosition(position);
                Intent iDetailActivity = new Intent(context, DetailActivity.class);
                iDetailActivity.putExtra(DetailActivity.EXTRA_DETAIL, detail);
                context.startActivity( iDetailActivity);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return serialFavorites.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgPoster;
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView deleteItems;
        private CardView cvContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvContainer = itemView.findViewById(R.id.cv_container);
            deleteItems = itemView.findViewById(R.id.delete_ic);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_desc);

        }
    }
}
