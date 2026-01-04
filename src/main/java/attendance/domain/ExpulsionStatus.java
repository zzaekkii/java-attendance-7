package attendance.domain;

public enum ExpulsionStatus {
    NONE("해당없음"),
    WARNING("경고"),
    COUNSELING("면담"),
    EXPULSION("제적");

    private final String status;

    ExpulsionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return "(" + status + ")";
    }
}
