package attendance;

import attendance.domain.Crew;
import attendance.domain.CrewList;
import attendance.view.FileView;
import attendance.view.InputView;
import attendance.view.OutputView;
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


    }

}
