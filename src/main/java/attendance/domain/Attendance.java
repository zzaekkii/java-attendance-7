package attendance.domain;

import attendance.day.Day;
import attendance.day.Time;

public class Attendance {
    private final Day day;
    private final Time time;

    public Attendance(Day day, Time time) {
        this.day = day;
        this.time = time;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }
}
