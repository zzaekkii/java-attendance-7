package attendance.view;

public class OutputView {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_MESSAGE_PREFIX + message);
    }
}
