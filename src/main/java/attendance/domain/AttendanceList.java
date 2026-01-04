package attendance.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceList {

    private final Map<Crew, List<Attendance>> attendanceList;

    public AttendanceList(Map<Crew, List<Attendance>> lists) {
        this.attendanceList = lists;
    }

    public static AttendanceList of(CrewList crews, List<AttendanceInfo> infos) {
        Map<Crew, List<Attendance>> logs = new HashMap<>();

        for (Crew crew : crews.getCrews()) {
            logs.put(crew, new ArrayList<>());
        }

        for (AttendanceInfo info : infos) {
            Crew crew = info.crew();
            Attendance attendance = new Attendance(info.day(), info.time());
            logs.get(crew).add(attendance);
        }

        return new AttendanceList(logs);
    }
}
