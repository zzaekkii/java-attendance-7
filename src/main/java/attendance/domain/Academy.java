package attendance.domain;

import java.time.LocalTime;

public class Academy {
    private static final LocalTime openTime = LocalTime.parse("08:00");
    private static final LocalTime closeTime = LocalTime.parse("23:00");

    private final Crews crews;

    public Academy(Crews crews) {
        this.crews = crews;
    }
}
