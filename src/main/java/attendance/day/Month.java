package attendance.day;

public enum Month {
    JAN(1, Week.WED, 31),
    DEC(12, Week.SUN, 31);

    private final int month;
    private final Week startWeek;
    private final int lastDay;

    Month(int month, Week startWeek, int lastDay) {
        this.month = month;
        this.startWeek = startWeek;
        this.lastDay = lastDay;
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

    public int getLastDay() {
        return lastDay;
    }
}
