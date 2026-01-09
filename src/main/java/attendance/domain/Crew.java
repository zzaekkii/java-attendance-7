package attendance.domain;

import attendance.domain.Day.Day;
import java.util.Map;

public class Crew {

    private final String name;
    private final Map<Day, Attendance> attendances;

    public Crew(String name, Map<Day, Attendance> attendances) {
        this.name = name;
        this.attendances = attendances;
    }
}
