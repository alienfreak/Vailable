package com.austin.elliott.vailable;

import java.util.Calendar;

public final class CalendarEvent {
    private String name;
    private Calendar startCalendar;
    private Calendar endCalendar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }
}
