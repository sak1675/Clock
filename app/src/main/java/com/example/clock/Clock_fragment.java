package com.example.clock;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Clock_fragment extends Fragment {

    TextView clock, date;
    ImageButton add;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    String timezones;
    long milis;
    LinearLayout linear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock,container,false);

        linear = view.findViewById(R.id.worldtimelayout);
        spinner = view.findViewById(R.id.spinner);
        add = view.findViewById(R.id.add_country);
        clock = view.findViewById(R.id.time);
        date = view.findViewById(R.id.date);


        Calendar current = Calendar.getInstance();
        SimpleDateFormat Time_format = new SimpleDateFormat("hh:mm");
        String Formatted_Time = Time_format.format(current.getTime());

        SimpleDateFormat Date_format = new SimpleDateFormat("dd-MMM-yyyy");
        String Formatted_Date = Date_format.format(current.getTime());

        clock.setText(Formatted_Time);
        date.setText( "Current: " + Formatted_Date);


        final String [] array_ID = TimeZone.getAvailableIDs();
        adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, array_ID);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.INVISIBLE);


        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        getCurrentTime();
                        String selectedTimeZone = (String) parent.getItemAtPosition(pos);

                       TimeZone timeZone = TimeZone.getTimeZone(selectedTimeZone);
                       String Timezone_Name = timeZone.getDisplayName();

                       int Timezone_Offset = timeZone.getRawOffset() / (60 * 1000);

                       int hours = Timezone_Offset / 60;
                       int minutes = Timezone_Offset % 60;

                       milis = milis + timeZone.getRawOffset();
                       timezones = (Timezone_Name + ""  + hours + ":" + minutes);
                       milis=0;

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                       //
                    }
                }
                );





        add.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                            spinner.setVisibility(View.VISIBLE);
                            TextView time_view = new TextView(getActivity());
                            time_view.setTextColor(Color.parseColor("#FFFFFF"));
                            time_view.setText(timezones);
                            time_view.setTextSize(22);
                            Toast.makeText(getActivity(), "You have added new time zone", Toast.LENGTH_SHORT).show();
                            linear.addView(time_view);
                    }
                }
        );

        return view;
    }


    public void getCurrentTime() {
        Calendar currentTime = Calendar.getInstance();
        milis = currentTime.getTimeInMillis();
        TimeZone currentTimezone = currentTime.getTimeZone();
        int offset = currentTimezone.getRawOffset();

        if(currentTimezone.inDaylightTime(new Date())){
            offset = offset + currentTimezone.getDSTSavings();
        }
        milis = milis - offset;


    }

}