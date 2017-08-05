package com.austin.elliott.vailable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateEvent extends AppCompatActivity {

    private EditText eventNameEditText;
    private TextView eventDateTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mStartTimeSetListener;
    private TimePickerDialog.OnTimeSetListener mEndTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventNameEditText = (EditText) findViewById(R.id.eventNameEditText);
        eventDateTextView = (TextView) findViewById(R.id.eventDateTextView);
        startTimeTextView = (TextView) findViewById(R.id.startTimeTextView);
        endTimeTextView = (TextView) findViewById(R.id.endTimeTextView);

        eventDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                eventDateTextView.setText(month + "/" + day + "/" + year);
            }
        };

        startTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        CreateEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartTimeSetListener,
                        hour, min, DateFormat.is24HourFormat(CreateEvent.this));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, hour);
                //instead of c.set(Calendar.HOUR, hour);
                c.set(Calendar.MINUTE, minute);

                String myFormat = hour < 10 || (hour > 12 && hour < 22) ? "h:mm a" : "hh:mm a";

                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String  formated_time = sdf.format(c.getTime());

                startTimeTextView.setText(formated_time);
            }
        };

        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int min = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        CreateEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndTimeSetListener,
                        hour, min, DateFormat.is24HourFormat(CreateEvent.this));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mEndTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY, hour);
                //instead of c.set(Calendar.HOUR, hour);
                c.set(Calendar.MINUTE, minute);

                String myFormat = hour < 10 || (hour > 12 && hour < 22) ? "h:mm a" : "hh:mm a";

                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String  formated_time = sdf.format(c.getTime());

                endTimeTextView.setText(formated_time);
            }
        };
    }
}
