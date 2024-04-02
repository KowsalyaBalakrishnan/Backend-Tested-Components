package thread;

public class ProcessTimeUtil {

    public static String getTimeConsumed(long timeInMilliseconds) {
        String timeConsumed;
        if (timeInMilliseconds > 1000) {
            long hours = (timeInMilliseconds / 1000) / 60 / 60 % 24;
            long minutes = (timeInMilliseconds / 1000) / 60 % 60;
            long seconds = (timeInMilliseconds / 1000) % 60;
            timeConsumed = hours + ": h " + minutes + ": m " + seconds + ": s";
        } else {
            timeConsumed = timeInMilliseconds + "ms";
        }
        return timeConsumed;
    }
}
