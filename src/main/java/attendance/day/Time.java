package attendance.day;

public record Time(
        int times
) {
    public static Time fromString(String time) {
        String[] values = time.split(":");

        if (values.length != 2) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        int hours = Integer.parseInt(values[0]);
        int minutes = Integer.parseInt(values[1]);

        validateTimeFormat(hours, minutes);

        return new Time(hours * 60 + minutes);
    }

    public boolean isEarlier(Time time) {
        return this.times < time.times;
    }

    public boolean isLater(Time time) {
        return this.times > time.times;
    }

    public Time addMinutes(int minutes) {
        return new Time(times + minutes);
    }

    public String getTimesAtString() {
        int hours = times / 60;
        int minutes = times % 60;

        return String.format("%02d:%02d", hours, minutes);
    }

    private static void validateTimeFormat(int hours, int minutes) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
