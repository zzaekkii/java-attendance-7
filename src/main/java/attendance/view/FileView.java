package attendance.view;

import attendance.day.Day;
import attendance.day.Time;
import attendance.domain.AttendanceInfo;
import attendance.domain.Crew;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileView {
    public List<AttendanceInfo> readAttendanceInfos() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/attendances.csv"));
            List<AttendanceInfo> attendanceInfos = new ArrayList<>();

            boolean firstLine = true;
            for (String line : lines) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] values = line.split("[, ]");
                Crew crew = new Crew(values[0]);
                Day day = Day.fromString(values[1]);
                Time time = Time.fromString(values[2]);

                attendanceInfos.add(new AttendanceInfo(crew, day, time));
            }

            return attendanceInfos;
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는데 실패했습니다.");
        }
    }


}
