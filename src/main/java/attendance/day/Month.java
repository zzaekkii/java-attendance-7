package attendance.day;

public enum Month {
    DEC(Week.SUN);

    private final Week startWeek;

    Month(Week startWeek) {
        this.startWeek = startWeek;
    }

    public Week getStartWeek() {
        return startWeek;
    }
}
