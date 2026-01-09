package attendance.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Attendance {
    private final Optional<LocalTime> time;
    private final AttendanceStatus status;

    public Attendance(Optional<LocalTime> time, AttendanceStatus status) {
        this.time = time;
        this.status = status;
    }

    public String getAttendanceAsString() {
        if (time.isEmpty()) {
            return "--:-- " + status.getStatusAsString();
        }
        return time.get().format(DateTimeFormatter.ofPattern("HH:mm")) + " " + status.getStatusAsString();
    }
}
