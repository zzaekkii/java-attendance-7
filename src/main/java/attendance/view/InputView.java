package attendance.view;

import attendance.day.Day;
import attendance.day.Time;
import attendance.domain.Command;
import attendance.domain.Crew;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class InputView {
    public Command readCommand() {
        String input = readAndValidate();

        if (!input.matches("1-4|Q")) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        return Command.of(input);
    }

    public Crew readCrew() {
        String input = readAndValidate();

        return new Crew(input);
    }

    public Time readTime() {
        String input = readAndValidate();

        return Time.fromString(input);
    }

    public Day readDay() {
        String input = readAndValidate();

        try {
            int day = Integer.parseInt(input);
            LocalDateTime now = DateTimes.now();
            return Day.fromSeparateDate(now.getYear(), now.getDayOfMonth(), day);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
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
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
