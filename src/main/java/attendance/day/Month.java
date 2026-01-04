package attendance.day;

public enum Month {
    DEC(12, Week.SUN);

    private final int month;
    private final Week startWeek;

    Month(int month, Week startWeek) {
        this.month = month;
        this.startWeek = startWeek;
    }

    public static Month fromInteger(int month) {
        for (Month m : Month.values()) {
            if (m.getMonth() == month) {
                return m;
            }
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    public int getMonth() {
        return month;
    }

    public Week getStartWeek() {
        return startWeek;
    }
}
