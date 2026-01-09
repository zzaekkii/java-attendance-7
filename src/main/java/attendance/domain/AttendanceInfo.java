package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public record AttendanceInfo(
        String name,
        LocalDate date,
        LocalTime time
) {
}
