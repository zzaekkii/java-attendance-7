package attendance.domain;

import java.util.List;

public record AttendanceLogs(
        List<String> attendances,
        int attendanceCount,
        int latenessCount,
        int absenceCount,
        PunishmentStatus punishmentStatus
) {
}
