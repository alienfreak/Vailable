package com.austin.elliott.vailable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    private TextView eventStartDateTextView;
    private TextView eventEndDateTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mEndDateSetListener;
    private TimePickerDialog.OnTimeSetListener mStartTimeSetListener;
    private TimePickerDialog.OnTimeSetListener mEndTimeSetListener;
    private FloatingActionButton savefloatingActionButton;
    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventNameEditText = (EditText) findViewById(R.id.eventNameEditText);
        eventStartDateTextView  = (TextView) findViewById(R.id.eventStartDateTextView);
        eventEndDateTextView  = (TextView) findViewById(R.id.eventEndDateTextView);
        startTimeTextView = (TextView) findViewById(R.id.startTimeTextView);
        endTimeTextView = (TextView) findViewById(R.id.endTimeTextView);
        savefloatingActionButton = (FloatingActionButton) findViewById(R.id.savefloatingActionButton);

        eventStartDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = startCalendar.get(Calendar.YEAR);
                int month = startCalendar.get(Calendar.MONTH);
                int day = startCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mStartDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                eventStartDateTextView.setText(month + "/" + day + "/" + year);
            }
        };

        eventEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = endCalendar.get(Calendar.YEAR);
                int month = endCalendar.get(Calendar.MONTH);
                int day = endCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateEvent.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mEndDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                eventEndDateTextView.setText(month + "/" + day + "/" + year);
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
                startCalendar.set(Calendar.HOUR_OF_DAY, hour);
                //instead of c.set(Calendar.HOUR, hour);
                startCalendar.set(Calendar.MINUTE, minute);

                String myFormat = hour < 10 || (hour > 12 && hour < 22) ? "h:mm a" : "hh:mm a";

                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String  formated_time = sdf.format(startCalendar.getTime());

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
                endCalendar.set(Calendar.HOUR_OF_DAY, hour);
                //instead of c.set(Calendar.HOUR, hour);
                endCalendar.set(Calendar.MINUTE, minute);

                String myFormat = hour < 10 || (hour > 12 && hour < 22) ? "h:mm a" : "hh:mm a";

                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String  formated_time = sdf.format(endCalendar.getTime());

                endTimeTextView.setText(formated_time);
            }
        };

        savefloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarEvent calendarEvent = new CalendarEvent();
                calendarEvent.setName(eventNameEditText.getText().toString().trim());
                calendarEvent.setStartCalendar(startCalendar);
                calendarEvent.setEndCalendar(endCalendar);
            }
        });
    }
}
