package com.mobiquity.testapp.testproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitparekh on 18/09/15.
 */
public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.DetailViewHolder> {

    List<Album> artistAlbumLists = new ArrayList<>();
    private Context context;

    public DetailRecyclerAdapter(List<Album> artistAlbumList){

        this.artistAlbumLists = artistAlbumList;
    }
    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                . inflate(R.layout.album_items, parent, false);

        DetailViewHolder viewHolder = new DetailViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {

        holder.albumName.setText(artistAlbumLists.get(position).getTitle());
        holder.albumType.setText(artistAlbumLists.get(position).getType());

        Picasso.with(this.context).load(artistAlbumLists.get(position).getPicture()).into(holder.albumImg);

    }

    @Override
    public int getItemCount() {
        return  this.artistAlbumLists.size();
    }



    public static class DetailViewHolder extends RecyclerView.ViewHolder{
        protected TextView albumName;
        protected TextView albumType;
        protected ImageView albumImg;
        public DetailViewHolder(View itemView) {
            super(itemView);
            albumName = (TextView) itemView.findViewById(R.id.albumTitle);
            albumType = (TextView) itemView.findViewById(R.id.albumType);
            albumImg = (ImageView)itemView.findViewById(R.id.imgAlbum);

        }


    }
}
