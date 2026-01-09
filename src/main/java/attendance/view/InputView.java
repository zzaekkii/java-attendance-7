package attendance.view;

import attendance.domain.Command;
import attendance.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InputView {
    public Command readCommand() {
        String input = readAndValidate();
        validateCommand(input);
        return Command.of(input);
    }

    public String readNickname() {
        return readAndValidate();
    }

    public LocalTime readAttendanceTime() {
        String input = readAndValidate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(input, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private void validateCommand(String input) {
        if (!input.matches("^[1-4Q]")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static String readAndValidate() {
        String input = readLine();

        nullCheck(input);

        input = input.trim();
        return input;
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static void nullCheck(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}
