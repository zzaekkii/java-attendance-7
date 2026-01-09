package attendance.controller;

import attendance.view.FileInputView;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AttendanceController {
    private final FileInputView fileInputView;
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(FileInputView fileInputView, InputView inputView, OutputView outputView) {
        this.fileInputView = fileInputView;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
    }
}
