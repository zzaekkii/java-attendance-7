package attendance.domain;

import attendance.domain.Day.Day;
import attendance.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Academy {
    private static final LocalTime openTime = LocalTime.parse("08:00");
    private static final LocalTime closeTime = LocalTime.parse("23:00");

    private final Crews crews;

    public Academy(Crews crews) {
        this.crews = crews;
    }

    public String modifyAttendance(Crew crew, LocalDate modifyDate, LocalTime modifyTime) {
        String previousLog = crew.getAttendanceOf(modifyDate).getAttendanceAsString();
        crew.modifyAttendance(modifyDate, modifyTime);
        String modifiedLog = crew.getAttendanceOf(modifyDate).getAttendanceAsString();
        return Day.getDayAsString(modifyDate) + " " + previousLog
                + " -> " + modifiedLog + " 수정 완료!";
    }

    public AttendanceLogs showPreviousAttendances(Crew crew) {
        List<String> attendances = new ArrayList<>();
        LocalDate today = DateTimes.now().toLocalDate();
        // 이번달 한정이라
        if (today.getDayOfMonth() == 1) {
            throw new IllegalArgumentException(ErrorMessage.ATTENDANCES_NOT_FOUND.getMessage());
        }

        LocalDate yesterday = today.minusDays(1);
        LocalDate firstDate = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
        for (LocalDate date = firstDate; !date.isAfter(yesterday); date = date.plusDays(1)) {
            if (Day.isWeekendOrHoliday(date)) {
                continue;
            }
            String dayInfo = Day.getDayAsString(date);
            String attendanceInfo = crew.getAttendanceOf(date).getAttendanceAsString();
            attendances.add(dayInfo + " " + attendanceInfo);
        }

        int attendanceCount = crew.getAttendanceCount(yesterday);
        int latenessCount = crew.getLatenessCount(yesterday);
        int absenceCount = crew.getAbsenceCount(yesterday);

        PunishmentStatus punishmentStatus = crew.getPunishmentStatus(yesterday);

        return new AttendanceLogs(attendances, attendanceCount, latenessCount, absenceCount, punishmentStatus);
    }

    public boolean isNotOperatingTime(LocalTime time) {
        return time.isBefore(openTime) || time.isAfter(closeTime);
    }

    public Crew getCrewByName(String name) {
        return crews.getCrewByName(name);
    }
}
