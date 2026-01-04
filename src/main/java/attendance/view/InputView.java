package attendance.view;

import attendance.domain.Command;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public Command readCommand() {
        String input = Console.readLine();

        nullCheck(input);

        input = input.trim();

        if (!input.matches("1-4|Q")) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        return Command.of(input);
    }

    private static void nullCheck(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
