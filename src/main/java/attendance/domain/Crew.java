package attendance.domain;

import attendance.exception.ErrorMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Crew {

    private final String name;
    private final Map<LocalDate, Attendance> attendances;

    public Crew(String name) {
        this.name = name;
        attendances = new HashMap<>();
    }

    public Attendance addAttendance(LocalDate date, LocalTime time) {
        if (attendances.containsKey(date)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_ATTENDANCE.getMessage());
        }
        Attendance attendance = Attendance.of(date, time);
        attendances.put(date, attendance);
        return attendance;
    }

    public void modifyAttendance(LocalDate date, LocalTime time) {
        Attendance attendance = Attendance.of(date, time);
        attendances.put(date, attendance);
    }

    public boolean isNotExistAttendance(LocalDate date) {
        return !attendances.containsKey(date);
    }

    public int getAttendanceCount(LocalDate until) {
        return getStatusCount(AttendanceStatus.ATTENDANCE, until);
    }

    public int getLatenessCount(LocalDate until) {
        return getStatusCount(AttendanceStatus.LATENESS, until);
    }

    public int getAbsenceCount(LocalDate until) {
        return getStatusCount(AttendanceStatus.ABSENCE, until);
    }

    public PunishmentStatus getPunishmentStatus(LocalDate yesterday) {
        return PunishmentStatus.of(getLatenessCount(yesterday) + getAbsenceCount(yesterday) * 3);
    }

    private int getStatusCount(AttendanceStatus status, LocalDate until) {
        int count = 0;
        for (Map.Entry<LocalDate, Attendance> entry : attendances.entrySet()) {
            if ((entry.getKey().isBefore(until) || entry.getKey().isEqual(until))
                    && status.equals(attendances.get(entry.getKey()).getStatus())) {
                count += 1;
            }
        }
        return count;
    }

    public Attendance getAttendanceOf(LocalDate date) {
        return attendances.get(date);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name) && Objects.equals(attendances, crew.attendances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attendances);
    }
}
