package com.example.clock;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Alarm_fragment extends Fragment {
    ImageButton addalarm;
    LinearLayout linear_alarm;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm,container,false);

        addalarm = view.findViewById(R.id.addalarmbtn);
        linear_alarm = view.findViewById(R.id.alarms);


        addalarm.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v){
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Fragment fragment = new set_alarm_fragment();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                }
        );

       /* if (linear_alarm != null) {
            TextView time_view = new TextView(getActivity());
            time_view.setTextColor(Color.parseColor("#FFFFFF"));
            time_view.setText("");
            time_view.setTextSize(42);
            Toast.makeText(getActivity(), "You have added new Alarm", Toast.LENGTH_SHORT).show();
            linear_alarm.addView(time_view);
        }
        */


        return view;
    }
}