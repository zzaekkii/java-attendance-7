package attendance.day;

public enum Holiday {
    CHRISTMAS(25);

    private final int day;

    Holiday(int day) {
        this.day = day;
    }

    public boolean isHoliday(int day) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.getDay() == day) {
                return true;
            }
        }
        return false;
    }

    public int getDay() {
        return day;
    }
}
