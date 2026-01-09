package attendance.view;

import attendance.domain.AttendanceInfo;
import attendance.domain.Crew;
import attendance.domain.CrewAttendanceInfo;
import attendance.domain.Crews;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileInputView {

    public CrewAttendanceInfo readCrewAttendanceInfo() throws Exception {
        Set<Crew> crews = new HashSet<>();
        List<AttendanceInfo> attendanceInfos = new ArrayList<>();
        LocalDate lastDate = LocalDate.parse("2024-12-01");

        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/attendances.csv"));
        boolean firstLine = true;
        for (String line : lines) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] values = line.split(",");
            String name = values[0];

            String[] dateTimes = values[1].split(" ");
            LocalDate date = LocalDate.parse(dateTimes[0]);
            LocalTime time = LocalTime.parse(dateTimes[1]);

            crews.add(new Crew(name));
            attendanceInfos.add(new AttendanceInfo(name, date, time));
            if (date.isAfter(lastDate)) {
                lastDate = date;
            }
        }

        return new CrewAttendanceInfo(new Crews(new ArrayList<>(crews)), attendanceInfos, lastDate.minusDays(1));
    }
}
