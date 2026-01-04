package attendance.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceList {

    private final CrewList crewList;
    private final Map<Crew, List<Attendance>> attendanceList;

    public AttendanceList(CrewList crews, Map<Crew, List<Attendance>> lists) {
        this.crewList = crews;
        this.attendanceList = lists;
    }

    public static AttendanceList of(CrewList crews, List<AttendanceInfo> infos) {
        Map<Crew, List<Attendance>> logs = new HashMap<>();

        for (Crew crew : crews.getCrews()) {
            logs.put(crew, new ArrayList<>());
        }

        for (AttendanceInfo info : infos) {
            Crew crew = info.crew();
            Attendance attendance = Attendance.of(info.day(), info.time());
            logs.get(crew).add(attendance);
        }

        return new AttendanceList(crews, logs);
    }

    public void enroll(Crew crew, Attendance tryEnroll) {
        if (!crewList.isExist(crew)) {
            throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
        }

        List<Attendance> attendances = attendanceList.get(crew);
        for (Attendance attendance : attendances) {
            if (attendance.getDay().equals(tryEnroll.getDay())) {
                throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
            }
        }

        attendances.add(tryEnroll);
    }
}
