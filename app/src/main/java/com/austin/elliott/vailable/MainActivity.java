package com.austin.elliott.vailable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.austin.elliott.vailable.utilities.FirebaseUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<String, String> calenderEvents = new HashMap<>();
    private ArrayList<CalendarEvent> calenderEventObjects = new ArrayList<>();
    private TextView eventNameTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        Button setAvailableTimes = (Button) findViewById(R.id.setAvailableTimesButton);
        eventNameTextView = (TextView) findViewById(R.id.eventNameTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);

        getUserCalendarEventKeys();

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiscUtils.switchToActivity(MainActivity.this, CreateEvent.class);
            }
        });

        setAvailableTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiscUtils.switchToActivity(MainActivity.this, CreateAvailability.class);
            }
        });
    }

    private void getUserCalendarEventKeys() {
        FirebaseUtils.getCurrentUserDBReference().child(FirebaseUtils.CALENDAR_EVENTS).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final Object value = dataSnapshot.getValue();
                        if (value != null) {
                            calenderEvents = (HashMap<String, String>) value;
                            getCalendarEventObjects();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void getCalendarEventObjects() {
        for (String key : calenderEvents.keySet())
        {
            FirebaseUtils.getCalendarEventsDBRef().child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final CalendarEvent calenderEvent = dataSnapshot.getValue(CalendarEvent.class);
                    if (calenderEvent != null) {
                        calenderEventObjects.add(calenderEvent);
                    }
                    if (calenderEventObjects.size() == calenderEvents.size()){
                        //we are on last item
                        displayEventList();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void displayEventList() {
        final CalendarEvent calendarEvent = calenderEventObjects.get(0);
        if (calendarEvent != null) {
            eventNameTextView.setText(calendarEvent.getEventName());
            final long startCalendarMillis = calendarEvent.getStartCalendarMillis();
            final long endCalendarMillis = calendarEvent.getEndCalendarMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM hh:mm a");
            String startDateString = formatter.format(new Date(startCalendarMillis));
            String endDateString = formatter.format(new Date(endCalendarMillis));
            dateTextView.setText(startDateString + " - " + endDateString);
        }
    }
}
