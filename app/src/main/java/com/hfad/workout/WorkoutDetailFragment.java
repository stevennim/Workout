package com.hfad.workout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {
    private long workoutId;//id of workout chosen by user; used to pick what info to display

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){//only create new instance of stopwatch if this is first time being created
            StopwatchFragment sf = new StopwatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();

            ft.add(R.id.stopwatch_container, sf);//add stopwatch to fragment
            ft.addToBackStack(null);//and also to back stack
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//FADING IN FADING OUT
            ft.commit();
        } else {//if app is already running, just displayed the workout instead of re-adding the fragment
            workoutId = savedInstanceState.getLong("workoutId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart(){//called when the fragment is about to become visible
        super.onStart();
        View v = getView();//gets the fragment's root View; needed to use findViewById

        if(v != null){
            TextView title = (TextView) v.findViewById(R.id.textTitle);
            Workout w = Workout.workouts[(int) workoutId];
            title.setText(w.getName());
            TextView description = (TextView) v.findViewById(R.id.textDesc);
            description.setText(w.getDescription());
        }
    }

    //runs before activity is destroyed; stores id of current workout displayed
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("workoutId", workoutId);
    }

    public void setWorkout(long wi){
        this.workoutId = wi;
    }
}
