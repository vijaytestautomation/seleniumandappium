package support;

import java.util.logging.Logger;

public class log {

    public log() {}

    // Initialize Log4j logs
    private static Logger log = Logger.getLogger(log.class.getName());

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName){
        log.info("****************************************************************************************");
        log.info("$$$$$$$$$$$$$$$$$$$$$     B__E_G_I__N            "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
        log.info("****************************************************************************************");
    }

    //This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName){
        log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-         " +sTestCaseName+"             XXXXXXXXXXXXXXXXXXXXXX");
    }

    // Need to create these methods, so that they can be called
    public static void info(String message) {
        log.info(message);
    }
}
