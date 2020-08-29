package com.example.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class set_alarm_fragment extends Fragment {

    ImageButton set_alarm;
    TimePicker pick_time;
    int hour_pick , minutes_pick;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_alarm,container,false);

        set_alarm = view.findViewById(R.id.set_alarm);
        pick_time = view.findViewById(R.id.add_time);


        pick_time.setOnTimeChangedListener(
                new TimePicker.OnTimeChangedListener(){
                    public void onTimeChanged(TimePicker view, int hour , int minutes){
                        hour_pick = hour;
                        minutes_pick = minutes;
                    }
                }
        );

        set_alarm.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){

                        AlarmManager set_alarm = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

                        for (i = 0; i < 20; i++) {
                        Date date = new Date();

                        Calendar time_now = Calendar.getInstance();
                        Calendar time_Alarm = Calendar.getInstance();

                        time_now.setTime(date);
                        time_Alarm.setTime(date);

                        time_Alarm.set(Calendar.HOUR_OF_DAY, hour_pick);
                        time_Alarm.set(Calendar.MINUTE,minutes_pick);
                        time_Alarm.set(Calendar.SECOND,0);

                        if(time_now.before(time_Alarm)){
                            time_Alarm.add(Calendar.DATE,1);

                        }

                            Intent intent = new Intent(getActivity(), Alarm_Receiver.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), i, intent, 0);
                            set_alarm.set(AlarmManager.RTC_WAKEUP, time_Alarm.getTimeInMillis(), pendingIntent);

                        }

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = new Alarm_fragment();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );

        return view;
    }

}