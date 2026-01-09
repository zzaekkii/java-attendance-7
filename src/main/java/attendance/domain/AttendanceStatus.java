package attendance.domain;

public enum AttendanceStatus {
    ATTENDANCE("출석"),
    LATENESS("지각"),
    ABSENCE("결석");

    private final String value;

    AttendanceStatus(String value) {
        this.value = value;
    }
    
    public String getStatusAsString() {
        return "(" + value + ")";
    }
}
