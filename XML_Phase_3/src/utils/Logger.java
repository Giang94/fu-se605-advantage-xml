package utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elune
 */
public class Logger {
    private static String logData = "";

    public synchronized static void log(String log)
    {
        if (log == null)
        {
            log = "<null>";
        }
        System.out.println(log);                
        logData += log + StringUtilities.NEW_LINE;
    }

    public static String getLogData() {
        return logData;
    }
        
}

