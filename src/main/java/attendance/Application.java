package attendance;

import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {
    public static void main(String[] args) {
        initialize();
        new AttendanceController(
                new InputView(),
                new OutputView()
        ).run();
    }

    private static void initialize() {
        
    }
}
