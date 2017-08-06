package com.austin.elliott.vailable.utilities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    private static FirebaseDatabase mFirebaseDatabase;

    private FirebaseUtils() {
        //prevent instantiation as this is a utility class.
        throw new IllegalAccessError("Utilities class");
    }

    public static FirebaseDatabase getDatabase() {
        if (mFirebaseDatabase == null) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseDatabase.setPersistenceEnabled(true);
        }
        return mFirebaseDatabase;
    }

    private static FirebaseAuth getAuthentication() {
        return FirebaseAuth.getInstance();
    }
}
