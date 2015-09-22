package com.mobiquity.testapp.testproject;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.graphics.Palette;

import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.Album;
import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.DataModelSingleton;
import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.DataModels;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DataModelSingleton dataModelSingletonInstance;
    private DataModels dataSource;

    @SuppressWarnings("ConstantConditions")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        this.dataModelSingletonInstance =  DataModelSingleton.getDataModelInstance();
        dataSource = this.dataModelSingletonInstance.getDataModels();

        setContentView(R.layout.activity_detail);

        long id = getIntent().getLongExtra("selectedID", 0);
        int selectedPostion = getIntent().getIntExtra("selectePosition",0);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "EXTRA_IMAGE");
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = dataSource.getArtists().get(selectedPostion).getName();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        final ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(dataSource.getArtists().get(selectedPostion).getPicture()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(itemTitle);
        TextView description = (TextView) findViewById(R.id.description);

        String textFromHtml = Jsoup.parse(dataSource.getArtists().get(selectedPostion).getDescription()).text();

        description.setText(textFromHtml);
        List<Album> artistAlbumList = new ArrayList<>();

        for (Album album:dataSource.getAlbums()) {
            if (album.getArtistId() == id){
                artistAlbumList.add(album);
            }
        }


        LinearLayout llCard = (LinearLayout) findViewById(R.id.llAlbumList);
        View viewGroup;
        LayoutInflater inflater = getLayoutInflater();
        TextView albumName;
        TextView albumType;
        ImageView albumImg;


        for (Album album : artistAlbumList) {

            viewGroup = inflater.inflate(R.layout.album_items, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            viewGroup.setLayoutParams(params);

            albumName = (TextView) viewGroup.findViewById(R.id.albumTitle);
            albumType = (TextView) viewGroup.findViewById(R.id.albumType);
            albumImg = (ImageView)viewGroup.findViewById(R.id.imgAlbum);
            albumName.setText(album.getTitle());
            albumType.setText(album.getType());
            Picasso.with(this).load(album.getPicture()).into(albumImg);
            llCard.addView(viewGroup);
        }

             /*   RecyclerView recList = (RecyclerView) findViewById(R.id.albumList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        int width= RecyclerView.LayoutParams.MATCH_PARENT;
        int height=(70*artistAlbumList.size())+(30*artistAlbumList.size());
      //  int height=RecyclerView.LayoutParams.MATCH_PARENT;
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);


        recList.setLayoutManager(llm);
        recList.setLayoutParams(parms);

       // recList.setNestedScrollingEnabled(false);
        if (artistAlbumList != null || !artistAlbumList.isEmpty()){
            recList.setVisibility(View.VISIBLE);

            recList.setAdapter(new DetailRecyclerAdapter(artistAlbumList));
        }else {
            recList.setVisibility(View.GONE);
        }*/

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(android.R.color.holo_red_light);
        int primary = getResources().getColor(android.R.color.holo_green_light);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        //updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(android.R.color.holo_orange_light));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }

}
