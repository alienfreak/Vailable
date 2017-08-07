package com.austin.elliott.vailable.utilities;

import com.austin.elliott.vailable.CalendarEvent;
import com.austin.elliott.vailable.dto.VailableUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    public static final String USERS = "users";
    public static final String CALENDAR_EVENTS = "calendarEvents";
    public static final String AVAILABILITY = "Availability";
    public static final String INFO = "info";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String PICTURE_KEY = "picture_key";

    public static final String CURRENTLY_AVAILABLE = "currentlyAvailable";
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

    public static void saveAvailability(CalendarEvent calendarEvent) {
        DatabaseReference newAvailability = getDatabaseRef().child(AVAILABILITY).push();
        calendarEvent.setCreatedBy(getCurrentUserUID());
        newAvailability.setValue(calendarEvent);
        getCurrentUserDBReference().child(AVAILABILITY).child(newAvailability.getKey())
                .setValue(calendarEvent.getEventName());
    }

    public static void saveUserInfo(VailableUser user) {
        DatabaseReference userInfo = getCurrentUserDBReference().child(INFO);

        if (user.getName() != null) {
            userInfo.child(NAME).setValue(user.getName());
        }

        if (user.getAge() != -1) {
            userInfo.child(AGE).setValue(user.getAge());
        }

        if (user.getEmail() != null) {
            userInfo.child(EMAIL).setValue(user.getEmail());
        }

        if (user.getPictureUri() != null) {
            userInfo.child(PICTURE_KEY).setValue(user.getPictureUri());
        }
    }

    public static void setUserCurrentlyAvailable(boolean available) {
        getCurrentUserDBReference().child(INFO).child(CURRENTLY_AVAILABLE).setValue(available);
    }
}
