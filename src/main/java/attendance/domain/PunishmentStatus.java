package attendance.domain;

public enum PunishmentStatus {
    NONE(""),
    WARNING("경고"),
    COUNSEL("면담"),
    WEEDING("제적");

    private final String value;

    PunishmentStatus(String value) {
        this.value = value;
    }

    public String getStatusAsString() {
        return "(" + value + ")";
    }
}
