package com.hfad.workout;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        if(savedInstanceState == null){//only create new instance of stopwatch if this is first time being created
            StopwatchFragment sf = new StopwatchFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.add(R.id.stopwatch_container, sf);//add stopwatch to fragment
            ft.addToBackStack(null);//and also to back stack
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//FADING IN FADING OUT
            ft.commit();
        }
    }
}
