package frc.team2220.robot.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
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

    private PrintStream filewriter;

    public Logger(String fileName) {
        try {
            File logFile = new File(fileName);
            filewriter = new PrintStream(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String string) {
        System.out.println(filewriter);
            filewriter.println(string);
//            filewriter.append("\n");
//            filewriter.flush();

    }

    public static void writeLog(String string) {
        Logger.instance.log(string);
    }

    public void close() {
            filewriter.close();

    }
}
