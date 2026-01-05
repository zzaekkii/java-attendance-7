package attendance.day;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Day(
        int year,
        Month month,
        int day,
        Week week
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Day fromSeparateDate(int year, int month, int day) {
        if (day < 1 || day > Month.fromInteger(month).getLastDay()) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
        return Day.fromString(year + "-" + month + "-" + day);
    }

    public static Day fromDate(LocalDateTime date) {
        return Day.fromString(date.format(FORMATTER));
    }

    public static Day fromString(String date) {
        String[] values = date.split("-");

        int year = Integer.parseInt(values[0]);
        Month month = Month.fromInteger(Integer.parseInt(values[1]));
        int day = Integer.parseInt(values[2]);
        Week week = Week.foundWeekAtMonth(month, day);

        return new Day(year, month, day, week);
    }

    public String getStringDate() {
        return String.format("%02d월 %02d일 %s",
                month.getMonth(),
                day,
                week.getWeek()
        );
    }

    public boolean isWeekend() {
        return week.isWeekend();
    }

    public boolean isHoliday() {
        for (Holiday holiday : Holiday.values()) {
            if (holiday.getDay() == day) {
                return true;
            }
        }
        return false;
    }
}
