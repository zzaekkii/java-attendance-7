package attendance.domain.Day;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Day {

    public static boolean isWeekend(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek == DayOfWeek.SATURDAY.getValue() || dayOfWeek == DayOfWeek.SUNDAY.getValue();
    }

    public String getDayAsString(LocalDate date) {
        String month = String.format("%02d월", date.getMonthValue());
        String day = String.format("%02d일", date.getDayOfMonth());
        String weekOfDay = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        return month + " " + day + " " + weekOfDay;
    }
}
