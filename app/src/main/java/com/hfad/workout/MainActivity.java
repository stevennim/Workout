package com.hfad.workout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //a method defined in the interface w/ WorkoutListFragment
    //implements the listener from WorkoutListFragment and starts DetailActivity when list item is clicked

    @Override
    public void itemClicked(long id){
        View fragmentContainer = findViewById(R.id.fragment_container);//fetch frame layout w/ detail fragment
        if(fragmentContainer != null){//on large screen (tablets), don't start DetailActivity
            WorkoutDetailFragment wdf = new WorkoutDetailFragment();
            //fetch the activity's fragment manager and begin fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            wdf.setWorkout(id);//set the workout to be displayed
            ft.replace(R.id.fragment_container, wdf);//replace old workout with new one
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//cool fading effect
            ft.addToBackStack(null);//adds everything that just changed (the transaction) to the back stack
            ft.commit();//all changes specified take effect

        } else {//on small screens, start DetailActivity
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(i);
        }
    }
}
