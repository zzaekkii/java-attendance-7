package attendance;

import attendance.day.Day;
import attendance.day.Time;
import attendance.domain.Crew;

public record AttendanceInfo(
        Crew crew,
        Day day,
        Time time
) {
}
