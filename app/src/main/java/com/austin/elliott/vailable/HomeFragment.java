package com.austin.elliott.vailable;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    private HashMap<String, String> calenderEvents = new HashMap<>();
    private ArrayList<CalendarEvent> calenderEventObjects = new ArrayList<>();
    private LinearLayout eventsLinearLayout;
    private boolean available = true;
    private View homeView;
    private CardView userAvailableCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        eventsLinearLayout = (LinearLayout) homeView.findViewById(R.id.upcomingEventsLinearLayout);
        TextView welcomeNameTextView = (TextView) homeView.findViewById(R.id.welcomeTextView);
        welcomeNameTextView.setText("Welcome,\n " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "!");

        userAvailableCardView = (CardView) homeView.findViewById(R.id.userAvailableCardView);
        userAvailableCardView.setOnClickListener(userAvailableListener);

        getUserCalendarEventKeys();

        //setup facebook profile pic
        ProfilePictureView profilePictureView;
        profilePictureView = (ProfilePictureView) homeView.findViewById(R.id.friendProfilePicture);
        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());

        return homeView;
    }

    final View.OnClickListener userAvailableListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //// TODO: 8/6/2017 change this to change the app theme colors via styles
            TextView userAvailableTextView = (TextView) userAvailableCardView.findViewById(R.id.userAvailableTextView);
            TextView friendsAvail = (TextView) homeView.findViewById(R.id.availableFriendsTextView);
            TextView upcomingEvents = (TextView) homeView.findViewById(R.id.upcomingEventsTextView);
            if (available) {
                userAvailableTextView.setText("You're currently not available");
                userAvailableCardView.setBackgroundColor(getResources().getColor(R.color.colorBusy));
                friendsAvail.setTextColor(getResources().getColor(R.color.colorBusy));
                upcomingEvents.setTextColor(getResources().getColor(R.color.colorBusy));
            } else {
                userAvailableTextView.setText("You're currently available");
                userAvailableCardView.setBackgroundColor(getResources().getColor(R.color.colorAvailable));
                friendsAvail.setTextColor(getResources().getColor(R.color.colorAvailable));
                upcomingEvents.setTextColor(getResources().getColor(R.color.colorAvailable));
            }
            available = !available;
            FirebaseUtils.setUserCurrentlyAvailable(available);
        }
    };

    private void getUserCalendarEventKeys() {
        FirebaseUtils.getCurrentUserDBReference().child(FirebaseUtils.CALENDAR_EVENTS).limitToFirst(5).
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
        if (calenderEventObjects != null) {
            for(CalendarEvent event : calenderEventObjects)
            {
                final long startCalendarMillis = event.getStartCalendarMillis();
                final long endCalendarMillis = event.getEndCalendarMillis();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM hh:mm a");
                String startDateString = formatter.format(new Date(startCalendarMillis));
                String endDateString = formatter.format(new Date(endCalendarMillis));

                LinearLayout eventTextGroup = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.event_text_group, null);
                final TextView eventNameTextView = (TextView) eventTextGroup.findViewById(R.id.eventNameTextView);
                final TextView eventDateTextView = (TextView) eventTextGroup.findViewById(R.id.dateTextView);
                eventNameTextView.setText(event.getEventName());
                eventDateTextView.setText(startDateString + " - " + endDateString);
                eventsLinearLayout.addView(eventTextGroup);
            }
        }
    }
}
