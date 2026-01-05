package attendance.view;

import attendance.day.Day;
import attendance.day.Time;
import attendance.domain.AttendanceStatus;
import camp.nextstep.edu.missionutils.DateTimes;

public class OutputView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }

    public void printCommandList() {
        String today = getToday();
        System.out.println("오늘은 " + today + "입니다. 기능을 선택해 주세요.");

        System.out.println("1. 출석 확인\n"
                + "2. 출석 수정\n"
                + "3. 크루별 출석 기록 확인\n"
                + "4. 제적 위험자 확인\n"
                + "Q. 종료");
    }

    public void printNicknameRequest() {
        System.out.println("닉네임을 입력해 주세요.");
    }

    public void printAttendanceTimeRequest() {
        System.out.println("등교 시간을 입력해 주세요.");
    }

    public void printNicknameToModifyRequest() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
    }

    public void printDayToModifyRequest() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
    }

    public void printAttendanceSuccess(Time time, AttendanceStatus status) {
        System.out.println(getToday() + " " + time.getTimesAtString() + " " + status.getValue());
    }

    private static String getToday() {
        return Day.fromDate(DateTimes.now()).getStringDate();
    }

    public void printTimeToModifyRequest() {
        System.out.println("언제로 변경하겠습니까?");
    }
}
