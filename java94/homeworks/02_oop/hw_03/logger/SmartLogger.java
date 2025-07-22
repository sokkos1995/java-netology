package logger;

import java.time.LocalDateTime;

public class SmartLogger implements Logger {
    private int logCnt = 0;
    @Override
    public void log(String msg) {
        logCnt++;
        String logLevel = "INFO";
        if (msg.toLowerCase().contains("error")) {
            logLevel = "ERROR";
        }
        System.out.println(logLevel + "#" + logCnt + " [" + LocalDateTime.now() + "] " + msg);
    }       
}
