package attendance.domain;

import attendance.day.Day;
import attendance.day.Time;

public record AttendanceInfo(
        Crew crew,
        Day day,
        Time time
) {
}
