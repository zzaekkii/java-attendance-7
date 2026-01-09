package attendance.controller;

import attendance.domain.Academy;
import attendance.domain.Attendance;
import attendance.domain.AttendanceInfo;
import attendance.domain.Crew;
import attendance.domain.CrewAttendanceInfo;
import attendance.domain.Crews;
import attendance.exception.ErrorMessage;
import attendance.view.FileInputView;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;

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
        Academy wooteco = getCrewsFromCsv();
    }

    private Academy getCrewsFromCsv() {
        try {
            // CSV 파일의 기록을 모두 등록
            CrewAttendanceInfo crewAttendanceInfo = fileInputView.readCrewAttendanceInfo();
            Crews crews = crewAttendanceInfo.crews();
            for (AttendanceInfo info : crewAttendanceInfo.attendanceInfos()) {
                Crew crew = crews.getCrewByName(info.name());
                crew.addAttendance(info.date(), Attendance.of(info.date(), info.time()));
            }

            // CSV 파일에 없는 날짜엔 결석으로 기록
            LocalDate lastDate = crewAttendanceInfo.lastDate();
            LocalDate firstDate = LocalDate.of(lastDate.getYear(), lastDate.getMonthValue(), 1);
            for (Crew crew : crews.getCrews()) {
                for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusDays(1)) {
                    if (crew.isNotExistAttendance(date)) {
                        crew.addAttendance(date, Attendance.of(date, null));
                    }
                }
            }
            return new Academy(crews);
        } catch (Exception e) {
            outputView.printErrorMessage(ErrorMessage.ETC.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
        }
    }
}
