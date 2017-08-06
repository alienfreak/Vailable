package com.austin.elliott.vailable;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.austin.elliott.vailable.utilities.FirebaseUtils;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HomeFragment extends Fragment {

    HashMap<String, String> calenderEvents = new HashMap<>();
    private ArrayList<CalendarEvent> calenderEventObjects = new ArrayList<>();
    private TextView eventNameTextView;
    private TextView dateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

//        Button createEventButton = (Button) findViewById(R.id.createEventButton);
//        Button setAvailableTimes = (Button) findViewById(R.id.setAvailableTimesButton);
//        eventNameTextView = (TextView) findViewById(R.id.eventNameTextView);
//        dateTextView = (TextView) findViewById(R.id.dateTextView);

//        getUserCalendarEventKeys();

//        createEventButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MiscUtils.switchToActivity(MainActivity.this, CreateEvent.class);
//            }
//        });
//
//        setAvailableTimes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MiscUtils.switchToActivity(MainActivity.this, CreateAvailability.class);
//            }
//        });

        TextView welcomNameTextView = (TextView) view.findViewById(R.id.welcomeTextView);
        welcomNameTextView.setText("Welcome\n " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "!");

        //setup facebook profile pic
        ProfilePictureView profilePictureView;
        profilePictureView = (ProfilePictureView) view.findViewById(R.id.friendProfilePicture);
        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
        return view;
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
