package attendance.domain;

public enum Command {
    CHECK_ATTENDANCE("1"),
    MODIFY_ATTENDANCE("2"),
    FOUND_CREWS_ATTENDANCES("3"),
    FOUND_DANGER("4"),
    QUIT("Q");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command of(String value) {
        for (Command command : Command.values()) {
            if (command.value.equals(value)) {
                return command;
            }
        }
        throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
    }
}
