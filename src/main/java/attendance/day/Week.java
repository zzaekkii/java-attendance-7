package attendance.day;

public enum Week {
    SUN("일요일", true),
    MON("월요일", false),
    TUE("화요일", false),
    WED("수요일", false),
    THU("목요일", false),
    FRI("금요일", false),
    SAT("토요일", true);

    private final String week;
    private final boolean weekend;

    Week(String week, boolean weekend) {
        this.week = week;
        this.weekend = weekend;
    }

    public boolean isWeekend() {
        return weekend;
    }
}
