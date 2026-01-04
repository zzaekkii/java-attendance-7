package attendance.day;

public enum Holiday {
    CHRISTMAS(25);

    private final int day;

    Holiday(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
