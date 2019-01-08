package com.hfad.workout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener{
    private int seconds = 0;
    private boolean running;//whether or not stopwatch is running
    private boolean wasRunning;//says whether or not stopwatch was running before activity was paused

    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        if(savedInstanceBundle != null){//restores timer and state of timer
            seconds = savedInstanceBundle.getInt("seconds");
            running = savedInstanceBundle.getBoolean("running");
            wasRunning = savedInstanceBundle.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(v);

        //attach click listener (this java file) to each button
        Button startButton = (Button) v.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) v.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) v.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onPause(){
        super.onPause();
        wasRunning = running;//saves state of timer
        running = false;//temporarily stops timer
    }

    @Override
    public void onResume(){
        super.onResume();
        if(wasRunning){//if timer was running before pause, resume it
            running = true;
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.start_button:
                onClickStart();
                break;

            case R.id.stop_button:
                onClickStop();
                break;

            case R.id.reset_button:
                onClickReset();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    private void onClickStart(){//if Start button is clicked, begin timer
        running = true;
    }

    private void onClickStop(){//if Start button is clicked, stop timer
        running = false;
    }

    private void onClickReset(){//if Start button is clicked, reset and stop timer
        running = false;
        seconds = 0;
    }

    private void runTimer(View view){//called regularily to update the timer
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post( new Runnable() {
            @Override
            public void run(){
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
