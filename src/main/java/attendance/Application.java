package attendance;

import attendance.view.FileView;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new AttendanceController(
                new FileView(),
                new InputView(),
                new OutputView()
        ).run();
    }
}
