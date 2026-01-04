package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
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

        // 공휴일이 아닌데 기록이 없는 경우 결석 처리
        LocalDateTime now = DateTimes.now();
        for (Crew crew : crews.getCrews()) {
            List<Attendance> attendances = logs.get(crew);
            for (int day = 1; day < now.getDayOfMonth(); day++) {
//                attendances.contains();
                // 아 결석처리
            }
        }

        return new AttendanceList(crews, logs);
    }

    public void enroll(Crew crew, Attendance tryEnroll) {
        validateExist(crew);

        List<Attendance> attendances = attendanceList.get(crew);
        for (Attendance attendance : attendances) {
            if (attendance.getDay().equals(tryEnroll.getDay())) {
                throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
            }
        }

        attendances.add(tryEnroll);
    }

    public void modify(Crew crew, Attendance tryModify) {
        validateExist(crew);

        List<Attendance> attendances = attendanceList.get(crew);

    }

    private void validateExist(Crew crew) {
        if (!crewList.isExist(crew)) {
            throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
        }
    }

}
