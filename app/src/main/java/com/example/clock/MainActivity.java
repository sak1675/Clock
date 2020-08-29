package com.example.clock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton clockbtn =  findViewById(R.id.Clock);
        ImageButton alarmbtn =  findViewById(R.id.Alarm);
        ImageButton timerbtn = findViewById(R.id.Timer);
        ImageButton stopwatchbtn = findViewById(R.id.Stopwatch);

        clockbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new Clock_fragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        alarmbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = new Alarm_fragment();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        timerbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = new Timer_fragment();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        stopwatchbtn.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        Fragment fragment = new Stopwatch_fragment();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int res_Id =  item.getItemId();
        if(res_Id==R.id.settings){
            Toast.makeText(getApplicationContext(), " you are in settings", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}