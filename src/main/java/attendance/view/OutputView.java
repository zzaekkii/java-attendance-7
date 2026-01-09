package attendance.view;

import attendance.domain.AttendanceLogs;
import attendance.domain.PunishmentStatus;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printCommandList(String today) {
        System.out.println("오늘은 " + today + "입니다. 기능을 선택해 주세요.");
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }

    public void printNicknameRequestForCheck() {
        System.out.println("닉네임을 입력해 주세요.");
    }

    public void printAttendanceTimeRequest() {
        System.out.println("등교 시간을 입력해 주세요.");
    }

    public void printCheckAttendanceSuccess(String date, String attendanceInfo) {
        System.out.println(date + " " + attendanceInfo + "\n");
    }

    public void printNicknameRequestForModify() {
        System.out.println("\n출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
    }

    public void printDateForModifyRequest() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
    }

    public void printModifyTimeRequest() {
        System.out.println("언제로 변경하겠습니까?");
    }

    public void printModifyAttendanceSuccess(String message) {
        System.out.println(message + "\n");
    }

    public void printAttendanceLogs(String name, AttendanceLogs attendanceLogs) {
        System.out.println("\n이번 달 빙티의 출석 기록입니다.\n");
        for (String attendance : attendanceLogs.attendances()) {
            System.out.println(attendance);
        }
        System.out.println();

        System.out.println("출석: " + attendanceLogs.attendanceCount() + "회");
        System.out.println("지각: " + attendanceLogs.latenessCount() + "회");
        System.out.println("결석: " + attendanceLogs.absenceCount() + "회");
        System.out.println();

        if (attendanceLogs.punishmentStatus().equals(PunishmentStatus.NONE)) {
            return;
        }
        System.out.println(attendanceLogs.punishmentStatus().getStatusAsString() + "\n");
    }
}
