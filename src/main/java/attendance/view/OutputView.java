package attendance.view;

import attendance.day.Day;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class OutputView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }

    public void printCommandList() {
        String today = getToday();
        System.out.println("오늘은 " + today + "입니다. 기능을 선택해 주세요.");

        System.out.println("1. 출석 확인"
                + "2. 출석 수정\n"
                + "3. 크루별 출석 기록 확인\n"
                + "4. 제적 위험자 확인\n"
                + "Q. 종료");
    }

    private static String getToday() {
        LocalDateTime now = DateTimes.now(); // 이런게.. 있었다니..
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        String today = Day.fromString(year + "-" + month + "-" + day).getStringDate();
        return today;
    }
}
