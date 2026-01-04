package attendance;

import attendance.day.Day;
import attendance.day.Time;
import attendance.domain.Attendance;
import attendance.domain.AttendanceInfo;
import attendance.domain.AttendanceList;
import attendance.domain.Command;
import attendance.domain.Crew;
import attendance.domain.CrewList;
import attendance.view.FileView;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttendanceController {

    private final FileView fileView;
    private final InputView inputView;
    private final OutputView outputView;

    public AttendanceController(FileView fileView, InputView inputView, OutputView outputView) {
        this.fileView = fileView;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // csv에서 읽어온 출석 기록
        List<AttendanceInfo> attendanceInfos = fileView.readAttendanceInfos();

        // 크루원 목록
        Set<Crew> crews = new HashSet<>();
        for (AttendanceInfo info : attendanceInfos) {
            crews.add(info.crew());
        }
        CrewList crewList = new CrewList(crews);

        // 출석부에 출석 기록 등록
        AttendanceList attendanceList = AttendanceList.of(crewList, attendanceInfos);

        while (true) {
            outputView.printCommandList();

            Command command = getCommand();

            if (command.equals(Command.CHECK_ATTENDANCE)) {
                outputView.printNicknameRequest();
                Crew crew = inputView.readCrew();

                outputView.printAttendanceTimeRequest();
                Time time = inputView.readTime();
                Attendance attendance = Attendance.of(Day.fromDate(DateTimes.now()), time);

                attendanceList.enroll(crew, attendance);
                outputView.printAttendanceSuccess(time, attendance.getAttendanceStatus());
            }

            if (command.equals(Command.MODIFY_ATTENDANCE)) {
                outputView.printNicknameToModifyRequest();
                Crew crew = inputView.readCrew();

                outputView.printDayToModifyRequest();
                Day day = inputView.readDay();

                outputView.printTimeToModifyRequest();
                Time time = inputView.readTime();
                Attendance attendance = Attendance.of(Day.fromDate(DateTimes.now()), time);

                attendanceList.modify(crew, attendance);
            }

            if (command.equals(Command.QUIT)) {
                break;
            }
        }
    }

    private Command getCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            System.exit(0);
        }
        return null;
    }

}
