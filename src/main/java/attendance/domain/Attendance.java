package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Attendance {
    private static final int LATENESS_BOUND = 5;
    private static final int ABSENCE_BOUND = 30;

    private final Optional<LocalTime> time;
    private final AttendanceStatus status;

    private Attendance(Optional<LocalTime> time, AttendanceStatus status) {
        this.time = time;
        this.status = status;
    }

    public static Attendance of(LocalDate date, LocalTime time) {
        if (time == null) {
            return new Attendance(Optional.empty(), AttendanceStatus.ABSENCE);
        }
        return new Attendance(Optional.of(time), judgeStatus(date, time));
    }

    public String getAttendanceAsString() {
        if (time.isEmpty()) {
            return "--:-- " + status.getStatusAsString();
        }
        return time.get().format(DateTimeFormatter.ofPattern("HH:mm")) + " " + status.getStatusAsString();
    }

    private static AttendanceStatus judgeStatus(LocalDate date, LocalTime time) {
        if (date.getDayOfWeek().getValue() == DayOfWeek.MONDAY.getValue()) {
            return judgeStatus(EducatingDay.MONDAY, time);
        }
        return judgeStatus(EducatingDay.OTHER_DAY, time);
    }

    private static AttendanceStatus judgeStatus(EducatingDay day, LocalTime time) {
        if (isAbsence(day, time)) {
            return AttendanceStatus.ABSENCE;
        }
        if (isLateness(day, time)) {
            return AttendanceStatus.LATENESS;
        }
        return AttendanceStatus.ATTENDANCE;
    }

    private static boolean isAbsence(EducatingDay day, LocalTime time) {
        return time.isAfter(day.getStartTime().plusMinutes(ABSENCE_BOUND));
    }

    private static boolean isLateness(EducatingDay day, LocalTime time) {
        return time.isAfter(day.getStartTime().plusMinutes(LATENESS_BOUND));
    }
}
