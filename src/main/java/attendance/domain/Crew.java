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
