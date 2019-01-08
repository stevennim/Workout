package com.hfad.workout;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;

/**
 * A simple {@link ListFragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {
    static interface Listener {
        void itemClicked(long id);
    };

    private Listener l;//add a listener to the fragment

    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout.workouts.length];

        for(int i = 0; i < names.length; i++){//populates an array with the names of each workout
            names[i] = Workout.workouts[i].getName();
        }
        //get the context and create the string adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);//bind adapter to the list (fragment) view

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context){//sets the listener, giving the fragment a reference to MainActivity
        super.onAttach(context);
        this.l = (Listener)context;//register MainActivity as a listener
    }

    //passes to the listener the id of the workout clicked on
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if(l != null){
            l.itemClicked(id);
        }
    }
}
