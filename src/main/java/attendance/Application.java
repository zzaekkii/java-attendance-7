package attendance;

import attendance.view.FileView;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {
        LocalDate.of(2024, 12, 13).atStartOfDay();
        new AttendanceController(
                new FileView(),
                new InputView(),
                new OutputView()
        ).run();
    }
}
