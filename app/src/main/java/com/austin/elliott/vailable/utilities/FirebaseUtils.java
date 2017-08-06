package com.austin.elliott.vailable.utilities;

import com.austin.elliott.vailable.CalendarEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    public static final String USERS = "users";
    public static final String CALENDAR_EVENTS = "calendarEvents";
    private static FirebaseDatabase mFirebaseDatabase;

    private FirebaseUtils() {
        //prevent instantiation as this is a utility class.
        throw new IllegalAccessError("Utilities class");
    }

    private static FirebaseDatabase getDatabase() {
        if (mFirebaseDatabase == null) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseDatabase.setPersistenceEnabled(true);
        }
        return mFirebaseDatabase;
    }

    private static FirebaseAuth getAuthentication() {
        return FirebaseAuth.getInstance();
    }

    private static DatabaseReference getDatabaseRef(){
        return getDatabase().getReference();
    }

    /*********************************************************************************************/
    /************************************** USER UTIL FUNCTIONS **********************************/
    /*********************************************************************************************/
    public static String getCurrentUserUID() {
        if (getAuthentication().getCurrentUser() != null) {
            return getAuthentication().getCurrentUser().getUid();
        } else {
            return null;
        }
    }

    public static DatabaseReference getCurrentUserDBReference() {
        String userUID = getCurrentUserUID();
        if (userUID != null) {
            return getDatabase().getReference().child(USERS).child(userUID);
        } else {
            return null;
        }
    }

    public static void saveCalendarEvent(CalendarEvent calendarEvent) {
        DatabaseReference newCalEvent = getDatabaseRef().child(CALENDAR_EVENTS).push();
        calendarEvent.setCreatedBy(getCurrentUserUID());
        newCalEvent.setValue(calendarEvent);
        getCurrentUserDBReference().child(CALENDAR_EVENTS).child(newCalEvent.getKey())
                .setValue(calendarEvent.getEventName());
    }

    public static DatabaseReference getCalendarEventsDBRef() {
        return getDatabaseRef().child(CALENDAR_EVENTS);
    }
}
