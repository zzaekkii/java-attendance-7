package attendance.exception;

public enum ErrorMessage {
    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    NICKNAME_NOT_FOUND("등록되지 않은 닉네임입니다."),
    WEEKEND_OR_HOLIDAY("은 등교일이 아닙니다."),
    MODIFY_NOW_ALLOWED("아직 수정할 수 없습니다."),
    NOT_OPERATING_HOUR("캠퍼스 운영 시간에만 출석이 가능합니다."),
    DUPLICATE_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.\n"),
    ATTENDANCES_NOT_FOUND("이번달 출석 기록이 없습니다."),
    ETC("작업 중 오류가 발생했습니다.");


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
