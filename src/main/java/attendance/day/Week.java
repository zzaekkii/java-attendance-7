package attendance.day;

public enum Week {
    SUN("일요일", true, 0),
    MON("월요일", false, 1),
    TUE("화요일", false, 2),
    WED("수요일", false, 3),
    THU("목요일", false, 4),
    FRI("금요일", false, 5),
    SAT("토요일", true, 6);

    private final String week;
    private final boolean weekend;
    private final int sequence;

    Week(String week, boolean weekend, int sequence) {
        this.week = week;
        this.weekend = weekend;
        this.sequence = sequence;
    }

    public static Week foundWeekAtMonth(Month month, int day) {
        int firstDay = month.getStartWeek().sequence;
        int nxt = ((day - 1) + firstDay) % 7;

        for (Week week : Week.values()) {
            if (week.sequence == nxt) {
                return week;
            }
        }
        throw new IllegalArgumentException("요일 계산 중 요류가 발생했습니다.");
    }

    public boolean isWeekend() {
        return weekend;
    }

    public String getWeek() {
        return week;
    }
}
