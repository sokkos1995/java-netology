package logger;

import java.time.LocalDateTime;

public class SmartLogger implements Logger {
    private int logCnt = 0;
    @Override
    public void log(String msg) {
        logCnt++;
        String logType = "INFO";
        if (msg.contains("error")) {
            logType = "ERROR";
        }
        LocalDateTime timestamp = LocalDateTime.now();
        System.out.println(logType + "#" + logCnt + " [" + timestamp + "] " + msg);
    }       
}
