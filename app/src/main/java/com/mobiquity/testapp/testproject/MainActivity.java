package com.mobiquity.testapp.testproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models.DataModelSingleton;


public class MainActivity extends ActionBarActivity {
    private RecyclerView recList;
    private DataModelSingleton dataModelSingletonInstance;
    String[] dataArray = new String[]{"India","Australia","USA","U.K","Japan","Russia","Germany","Pakistan","Bangladesh","Africa","Brazil","Rome","italy",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.dataModelSingletonInstance =  DataModelSingleton.getDataModelInstance();
        if(this.dataModelSingletonInstance.getDataModels() ==null ){
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        recList.setAdapter(new MainRecyclerAdapter());

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        recList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(),e.getY());



                if(child!=null && mGestureDetector.onTouchEvent(e)){

                    //  DetailActivity.navigate(this, rv.findViewById(R.id.image), viewModel);

                    int position = rv.getChildPosition(child);
                    long id = dataModelSingletonInstance.getDataModels().getArtists().get(position).getId();
                    Toast.makeText(MainActivity.this, "The Item Clicked is: " + rv.getChildPosition(child)+" id = "+id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("selectedID",id);
                    intent.putExtra("selectePosition",position);
                    MainActivity.this.startActivity(intent);
                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
