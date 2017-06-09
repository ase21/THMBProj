package ase.thmbproj.dataLayer.mainConnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ase.thmbproj.R;
import ase.thmbproj.presentationLayer.filmViev.view.FilmActivity;

/**
 * Created by ase911 on 24.05.2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public static final String POSITION = "position";
    private List<FilmModel> filmModelList = new ArrayList<>();
    private Context context;

    public MainAdapter(List<FilmModel> films){
        this.filmModelList = films;
    }

    public MainAdapter(Context contexts){
        this.context = context;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        FilmModel filmModel = filmModelList.get(position);
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w640"+filmModel.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return filmModelList.size();
    }

    public void setData(List<FilmModel> newData) {
        Log.d("myLogs", "Вывод результата");
        filmModelList = newData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViev);
            itemView.setOnClickListener(this);//подцепляем onClick
        }

        @Override
        public void onClick(View itemView) {

            Intent intent = new Intent(itemView.getContext(), FilmActivity.class);
            intent.putExtra("position", filmModelList.get(getAdapterPosition()));
            itemView.getContext().startActivity(intent);
        }
    }


}
