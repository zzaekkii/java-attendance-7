package attendance.domain;

import java.time.LocalDate;
import java.util.List;

public record CrewAttendanceInfo(
        Crews crews,
        List<AttendanceInfo> attendanceInfos,
        LocalDate lastDate
) {
}
