package attendance.day;

public record Day(
        int year,
        Month month,
        int day,
        Week week
) {
    public static Day fromString(String date) {
        String[] values = date.split("-");

        int year = Integer.parseInt(values[0]);
        Month month = Month.fromInteger(Integer.parseInt(values[1]));
        int day = Integer.parseInt(values[2]);
        Week week = Week.foundWeekAtMonth(month, day);

        return new Day(year, month, day, week);
    }
}
