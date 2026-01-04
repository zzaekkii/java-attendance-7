package attendance.domain;

import attendance.day.Day;
import attendance.day.Time;
import attendance.day.Week;

public class Attendance {

    private static final Time SCHOOL_OPEN = Time.fromString("08:00");
    private static final Time SCHOOL_CLOSED = Time.fromString("23:00");

    private static final Time TRAINING_START_MONDAY = Time.fromString("13:00");
    private static final Time TRAINING_START_OTHER_DAY = Time.fromString("10:00");
    private static final Time TRAINING_END = Time.fromString("18:00");

    private static final int LATE_BOUND = 5;
    private static final int ABSENCE_BOUND = 30;

    private final Day day;
    private final Time time;
    private final AttendanceStatus attendanceStatus;

    private Attendance(Day day, Time time, AttendanceStatus attendanceStatus) {
        this.day = day;
        this.time = time;
        this.attendanceStatus = attendanceStatus;
    }

    public static Attendance of(Day day, Time time) {
        if (isNotSchoolDay(day)) {
            throw new IllegalArgumentException(day.getStringDate() + "은 등교일이 아닙니다.");
        }

        if (isNotOperating(day, time)) {
            throw new IllegalArgumentException("캠퍼스 운영 시간에만 출석이 가능합니다.");
        }

        AttendanceStatus status = checkStatus(day, time);

        return new Attendance(day, time, status);
    }

    private static boolean isNotSchoolDay(Day day) {
        return day.isHoliday() || day.isWeekend();
    }

    private static boolean isNotOperating(Day day, Time time) {
        return time.isEarlier(SCHOOL_OPEN) || time.isLater(SCHOOL_CLOSED);
    }

    private static AttendanceStatus checkStatus(Day day, Time time) {
        if (day.week().equals(Week.MON)) {
            if (time.isLater(TRAINING_START_MONDAY.addMinutes(ABSENCE_BOUND))) {
                return AttendanceStatus.ABSENCE;
            }

            if (time.isLater(TRAINING_START_MONDAY.addMinutes(LATE_BOUND))) {
                return AttendanceStatus.LATE;
            }

            return AttendanceStatus.ATTENDANCE;
        }

        if (time.isLater(TRAINING_START_OTHER_DAY.addMinutes(ABSENCE_BOUND))) {
            return AttendanceStatus.ABSENCE;
        }

        if (time.isLater(TRAINING_START_OTHER_DAY.addMinutes(LATE_BOUND))) {
            return AttendanceStatus.LATE;
        }

        return AttendanceStatus.ATTENDANCE;
    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }
}
