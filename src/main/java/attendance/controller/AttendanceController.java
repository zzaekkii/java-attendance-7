package attendance.controller;

import attendance.domain.Academy;
import attendance.domain.Attendance;
import attendance.domain.AttendanceInfo;
import attendance.domain.Command;
import attendance.domain.Crew;
import attendance.domain.CrewAttendanceInfo;
import attendance.domain.Crews;
import attendance.domain.Day.Day;
import attendance.exception.ErrorMessage;
import attendance.view.FileInputView;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;

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
        while (true) {
            Command command = getCommand();

            if (Command.CHECK_ATTENDANCE.equals(command)) {
                checkAttendance(wooteco);
            }
        }
    }

    private void checkAttendance(Academy wooteco) {
        try {
            LocalDate today = DateTimes.now().toLocalDate();
            validateAttendanceDay(today);

            Crew crew = getCrew(wooteco);
            LocalTime attendanceTime = getAttendanceTime(wooteco);

            Attendance attendance = crew.addAttendance(today, attendanceTime);
            outputView.printCheckAttendanceSuccess(Day.getDayAsString(today), attendance.getAttendanceAsString());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(ErrorMessage.ETC.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
        }
    }

    private LocalTime getAttendanceTime(Academy wooteco) {
        outputView.printAttendanceTimeRequest();
        LocalTime attendanceTime = inputView.readAttendanceTime();
        if (wooteco.isNotOperatingTime(attendanceTime)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_OPERATING_HOUR.getMessage());
        }
        return attendanceTime;
    }

    private Crew getCrew(Academy wooteco) {
        outputView.printNicknameRequest();
        return wooteco.getCrewByName(inputView.readNickname());
    }

    private static void validateAttendanceDay(LocalDate today) {
        if (Day.isWeekendOrHoliday(today)) {
            throw new IllegalArgumentException(
                    Day.getDayAsString(today) + ErrorMessage.WEEKEND_OR_HOLIDAY.getMessage());
        }
    }

    private Command getCommand() {
        try {
            outputView.printCommandList(Day.getDayAsString(DateTimes.now().toLocalDate()));
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(ErrorMessage.ETC.getMessage());
            throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
        }
    }

    private Academy getCrewsFromCsv() {
        try {
            // CSV 파일의 기록을 모두 등록
            CrewAttendanceInfo crewAttendanceInfo = fileInputView.readCrewAttendanceInfo();
            Crews crews = crewAttendanceInfo.crews();
            for (AttendanceInfo info : crewAttendanceInfo.attendanceInfos()) {
                Crew crew = crews.getCrewByName(info.name());
                crew.addAttendance(info.date(), info.time());
            }

            // CSV 파일에 없는 날짜엔 결석으로 기록
            LocalDate lastDate = crewAttendanceInfo.lastDate();
            LocalDate firstDate = LocalDate.of(lastDate.getYear(), lastDate.getMonthValue(), 1);
            for (Crew crew : crews.getCrews()) {
                for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusDays(1)) {
                    if (crew.isNotExistAttendance(date)) {
                        crew.addAttendance(date, null);
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
