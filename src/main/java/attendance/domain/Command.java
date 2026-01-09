package attendance.domain;

import attendance.exception.ErrorMessage;

public enum Command {
    CHECK_ATTENDANCE("1"),
    MODIFY_ATTENDANCE("2"),
    FIND_CREW_ATTENDANCES("3"),
    FIND_PUNISHMENT_CREWS("4"),
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
        throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
    }
}
