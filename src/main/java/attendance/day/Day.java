package attendance.day;

public record Day(
        Month month,
        int day,
        Week week,
        boolean holiday
) {
    public boolean isHoliday() {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.getDay() == day) {
                return true;
            }
        }
        return false;
    }
}
