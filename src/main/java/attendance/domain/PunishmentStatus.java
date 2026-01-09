package attendance.domain;

public enum PunishmentStatus {
    NONE("", 0),
    WARNING("경고", 2 * 3),
    COUNSEL("면담", 3 * 3),
    WEEDING("제적", 5 * 3);

    private final String value;

    private final int bounds;

    PunishmentStatus(String value, int bounds) {
        this.value = value;
        this.bounds = bounds;
    }

    public static PunishmentStatus of(int latenessCount) {
        if (latenessCount >= WEEDING.bounds) {
            return WEEDING;
        }
        if (latenessCount >= COUNSEL.bounds) {
            return COUNSEL;
        }
        if (latenessCount >= WARNING.bounds) {
            return WARNING;
        }
        return NONE;
    }

    public String getStatusAsString() {
        if (this.equals(NONE)) {
            return "";
        }
        return value + " 대상자입니다.";
    }

    public String getValueAsString() {
        return "(" + value + ")";
    }
}
