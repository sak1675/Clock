package com.example.clock;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;


public class Timer_fragment extends Fragment {



    ImageButton startbtn;
    ImageButton stopbtn;

    EditText timer_hr, timer_min,timer_sec;
    private CountDownTimer countdowntimer;
    private boolean runtimer;

    private long Stat_Time_in_millies;
    private long time_left;
    String hour,min,sec;
    int timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        startbtn = view.findViewById(R.id.start_timer_btn);
        stopbtn = view.findViewById(R.id.stop_timer_btn);
        timer_hr = view.findViewById(R.id.text_timer_hr);
        timer_min = view.findViewById(R.id.text_timer_min);
        timer_sec = view.findViewById(R.id.text_timer_sec);


        startbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                        if (runtimer) {
                            pauseTimer();
                        } else {
                            hour = timer_hr.getText().toString();
                            min = timer_min.getText().toString();
                            sec = timer_sec.getText().toString();

                            timer = ((Integer.parseInt(hour) )*60) + (Integer.parseInt(min)) + (Integer.parseInt(sec)/60) ;
                            String user_input = Integer.toString(timer);
                            if (user_input =="0" ) {
                                Toast.makeText(getActivity(), "Please enter a  valid value", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (user_input == " ") {
                                Toast.makeText(getActivity(), "You left the field empty", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            long millisInput = Long.parseLong(user_input) * 60000;

                            setTime(millisInput);
                            startTimer();

                        }
                    }
                }
        );
        stopbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        stopTimer();
                    }
                }
        );

        formatCountDownText();

        return view;
    }
    //method to set time for countdown
    private void setTime(long milliseconds) {
        Stat_Time_in_millies = milliseconds;
        stopTimer();
    }

    //method to start timer
    //obtains time to countdown then sends to formatcoundown method in order to get correct format
    private void startTimer() {
        countdowntimer = new CountDownTimer(time_left, 1000) {
            @Override
            public void onTick(long l) {
                time_left = l;
                formatCountDownText();
            }

            @Override
            public void onFinish() {
                runtimer = false;
                startbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                startbtn.setVisibility(View.INVISIBLE);
                stopbtn.setVisibility(View.VISIBLE);

            }
        }.start(); //as method is called the timer starts


        runtimer = true;
        startbtn.setImageResource(R.drawable.ic_baseline_pause_24);
        stopbtn.setVisibility(View.INVISIBLE);
        timer_hr.setEnabled(false);
        timer_min.setEnabled(false);
        timer_sec.setEnabled(false);



    }

    //this methods is used to place hours in hours section minutes in minutes section by converting them
    private void formatCountDownText() {
        int hours = (int) ((time_left / 1000) /60) / 60;
        int minutes = (int) ((time_left / 1000) / 60) % 60;
        int seconds = (int) (time_left / 1000) % 60;

        //to put obtained time left in hour minutes and second format

            timer_hr.setText(String.format(Locale.getDefault(), "%02d",hours));
            timer_min.setText(String.format(Locale.getDefault(), "%02d",minutes));
            timer_sec.setText(String.format(Locale.getDefault(), "%02d",seconds));

    }

    // this method is called when stopwatch is paused
    // this methos pauses the coundown and gives us option to stop the stopwatch
    private void pauseTimer() {
        countdowntimer.cancel();
        runtimer=false;
        startbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        stopbtn.setVisibility(View.VISIBLE);
    }

    //this method is used for stoping the stopwatch
    private void stopTimer() {
        //when the time is reset is will return time_left value to initial value
        time_left = Stat_Time_in_millies;
        formatCountDownText();
        stopbtn.setVisibility(View.INVISIBLE);
        startbtn.setVisibility(View.VISIBLE);
        timer_hr.setText("00");
        timer_min.setText("00");
        timer_sec.setText("00");
        timer_hr.setEnabled(true);
        timer_min.setEnabled(true);
        timer_sec.setEnabled(true);
    }


}