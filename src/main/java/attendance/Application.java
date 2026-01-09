package attendance;

import attendance.controller.AttendanceController;
import attendance.view.FileInputView;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new AttendanceController(
                new FileInputView(),
                new InputView(),
                new OutputView()
        ).run();
    }
}
