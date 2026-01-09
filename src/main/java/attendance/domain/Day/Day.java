package attendance.domain.Day;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

public class Day {
    private final LocalDate date;

    public Day(LocalDate date) {
        this.date = date;
    }

    public boolean isWeekend() {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek == DayOfWeek.SATURDAY.getValue() || dayOfWeek == DayOfWeek.SUNDAY.getValue();
    }

    public String getDayAsString() {
        String month = String.format("%02d월", date.getMonthValue());
        String day = String.format("%02d일", date.getDayOfMonth());
        String weekOfDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        return month + " " + day + " " + weekOfDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Day day = (Day) o;
        return Objects.equals(date, day.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }
}
