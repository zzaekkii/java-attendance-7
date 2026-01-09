package attendance.domain;

import java.time.LocalTime;

public enum EducatingDay {
    MONDAY(LocalTime.parse("13:00"), LocalTime.parse("18:00")),
    OTHER_DAY(LocalTime.parse("10:00"), LocalTime.parse("18:00"));

    private final LocalTime startTime;
    private final LocalTime endTime;

    EducatingDay(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
