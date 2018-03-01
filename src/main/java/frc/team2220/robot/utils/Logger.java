package frc.team2220.robot.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static Logger instance = createLogger();

    private static Logger createLogger() {

        return new Logger("/home/lvuser/logs/log_" + getCurrentTimeStamp() + ".txt");
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    private FileWriter filewriter;

    private Logger(String fileName) {
        try {
            filewriter = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String string) {
        try {
            filewriter.append(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLog(String string) {
        Logger.instance.log(string);
    }

    public void close() {
        try {
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
