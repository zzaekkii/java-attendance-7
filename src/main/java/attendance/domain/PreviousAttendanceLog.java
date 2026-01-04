package attendance.domain;

import attendance.day.Day;
import attendance.day.Time;

public record PreviousAttendanceLog(
        Day day,
        Time time,
        AttendanceStatus status
) {
    public String getValue() {
        return day.getStringDate() + " " + time.getTimesAtString() + " " + status.getValue();
    }
}
