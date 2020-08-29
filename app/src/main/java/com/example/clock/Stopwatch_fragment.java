package com.example.clock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;


public class Stopwatch_fragment extends Fragment {

    private Chronometer stop_watch;
    private long start, millisec, update_time, increment = 0;
    private boolean stopwatch_running;
    private ImageButton stopwatch_start, stopwatch_stop;
    int sec, min, millis;
    Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        //referencing buttons
        stop_watch = view.findViewById(R.id.stopwatch_count);
        stopwatch_start = view.findViewById(R.id.stopwatch_start);
        stopwatch_stop = view.findViewById(R.id.stopwatch_stop);

        handler = new Handler();
        //adding ClickListener in button

        stopwatch_start.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        if (stopwatch_running) {
                            increment += millisec;
                            handler.removeCallbacks(runnable);
                            stop_watch.stop();
                            stopwatch_running = false;
                            stopwatch_start.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                            stopwatch_stop.setVisibility(View.VISIBLE);

                        } else {
                            start = SystemClock.uptimeMillis();
                            handler.postDelayed(runnable,0);
                            stop_watch.start();
                            stopwatch_running = true;
                            stopwatch_start.setImageResource(R.drawable.ic_baseline_pause_24);
                            stopwatch_stop.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );

        stopwatch_stop.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        start= 0;
                        millisec= 0;
                        update_time= 0;
                        increment = 0;
                        sec= 0;
                        min= 0;
                        millis= 0;
                        stop_watch.setText("00:00:00");
                        start = 0;
                        stop_watch.stop();
                        stopwatch_running = false;

                    }
                }
        );
        return view;
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisec = SystemClock.uptimeMillis() - start;
            update_time = increment + millisec;
            sec = (int) (update_time / 1000);
            min = sec / 60;
            sec = sec % 60; //gives remaining seconds.
            millis = (int) (update_time % 100);


            stop_watch.setText(String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + String.format("%02d", millis));

            handler.postDelayed(this, 30);
        }
    };
}

