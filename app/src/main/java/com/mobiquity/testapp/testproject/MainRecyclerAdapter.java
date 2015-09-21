package com.mobiquity.testapp.testproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.DataModelSingleton;
import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.DataModels;
import com.squareup.picasso.Picasso;

/**
 * Created by amitparekh on 17/09/15.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private DataModelSingleton dataModelSingletonInstance;
    private DataModels dataSource;
    private Context context;
    public MainRecyclerAdapter(){
        this.dataModelSingletonInstance =  DataModelSingleton.getDataModelInstance();
        dataSource = this.dataModelSingletonInstance.getDataModels();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                . inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textView.setText(dataSource.getArtists().get(position).getName());
        holder.textName.setText(dataSource.getArtists().get(position).getGenres());

        Picasso.with(this.context).load(dataSource.getArtists().get(position).getPicture().toString()).into(holder.imgThumb);

    }

    @Override
    public int getItemCount() {
        return dataSource.getArtists().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView textView;
        protected TextView textName;
        protected ImageView imgThumb;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            textName = (TextView) itemView.findViewById(R.id.txtName);
            imgThumb = (ImageView)itemView.findViewById(R.id.imgArtist);

        }


    }
}
