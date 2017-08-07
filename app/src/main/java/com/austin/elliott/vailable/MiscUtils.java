package com.austin.elliott.vailable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

final class MiscUtils {

    private MiscUtils() {
        //prevent instantiation as this is a utility class.
        throw new IllegalAccessError("Utilities class");
    }

    public static void switchToActivity(Context context, Class<? extends Activity> activityToLoad){
        Intent myIntent = new Intent(context, activityToLoad);
        context.startActivity(myIntent);
    }

    public static void switchToActivityClearBackStack(Context context, Class<? extends Activity> activityToLoad){
        Intent myIntent = new Intent(context, activityToLoad);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(myIntent);
    }
}
