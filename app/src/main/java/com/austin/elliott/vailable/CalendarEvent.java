package com.austin.elliott.vailable;

public final class CalendarEvent {
    private String eventName;
    private long startCalendarMillis;
    private long endCalendarMillis;
    private String createdBy;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getStartCalendarMillis() {
        return startCalendarMillis;
    }

    public void setStartCalendarMillis(long startCalendarMillis) {
        this.startCalendarMillis = startCalendarMillis;
    }

    public long getEndCalendarMillis() {
        return endCalendarMillis;
    }

    public void setEndCalendarMillis(long endCalendarMillis) {
        this.endCalendarMillis = endCalendarMillis;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
