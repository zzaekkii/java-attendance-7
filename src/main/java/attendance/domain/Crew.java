package attendance.domain;

import attendance.domain.Day.Day;
import attendance.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.DateTimes;
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

    public void addAttendance(LocalDate date, LocalTime time) {
        if (attendances.containsKey(date)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_ATTENDANCE.getMessage());
        }
        Attendance attendance = Attendance.of(date, time);
        attendances.put(date, attendance);
    }

    public void modifyAttendance(LocalDate date, LocalTime time) {
        Attendance attendance = Attendance.of(date, time);
        attendances.put(date, attendance);
    }

    public boolean isNotExistAttendance(LocalDate date) {
        return !attendances.containsKey(date);
    }

    public int getAttendanceCount() {
        LocalDate yesterday = DateTimes.now().toLocalDate().minusDays(1);
        return getStatusCount(AttendanceStatus.ATTENDANCE, yesterday);
    }

    public int getLatenessCount() {
        LocalDate yesterday = DateTimes.now().toLocalDate().minusDays(1);
        return getStatusCount(AttendanceStatus.LATENESS, yesterday);
    }

    public int getAbsenceCount() {
        LocalDate yesterday = DateTimes.now().toLocalDate().minusDays(1);
        return getStatusCount(AttendanceStatus.ABSENCE, yesterday);
    }

    public int getTotalLatenessCount() {
        return getLatenessCount() + getAbsenceCount();
    }

    public PunishmentStatus getPunishmentStatus() {
        return PunishmentStatus.of(getLatenessCount() + getAbsenceCount() * 3);
    }

    private int getStatusCount(AttendanceStatus status, LocalDate until) {
        int count = 0;
        for (Map.Entry<LocalDate, Attendance> entry : attendances.entrySet()) {
            if (Day.isWeekendOrHoliday(entry.getKey())) {
                continue;
            }

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
