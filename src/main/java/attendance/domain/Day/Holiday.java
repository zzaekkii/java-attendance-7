package attendance.domain.Day;

import java.time.LocalDate;

public enum Holiday {
    CHRISTMAS(LocalDate.parse("2024-12-25"));

    private final LocalDate date;

    Holiday(LocalDate date) {
        this.date = date;
    }

    public static boolean isHoliday(LocalDate date) {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.date.getMonthValue() == date.getMonthValue()
                    && holiday.date.getDayOfMonth() == date.getDayOfMonth()) {
                return true;
            }
        }
        return false;
    }
}
