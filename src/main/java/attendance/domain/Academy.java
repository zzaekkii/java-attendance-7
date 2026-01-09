package attendance.domain;

import attendance.domain.Day.Day;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public boolean isNotOperatingTime(LocalTime time) {
        return time.isBefore(openTime) || time.isAfter(closeTime);
    }

    public Crew getCrewByName(String name) {
        return crews.getCrewByName(name);
    }
}
