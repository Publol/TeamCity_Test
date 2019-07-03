package core.junit;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class Assertions {
    private static final Logger logger = Logger.getLogger(Assertions.class);
    private static final String assertMessage = "Assertion failed: %s";
    public static void assertTrue(String msg, boolean condition){
        try {
            Assert.assertTrue(condition);
        } catch (AssertionError ae) {
            logger.error(String.format(assertMessage, msg));
        }
    }

    public static void assertFalse(String msg, boolean condition){
        try {
            Assert.assertFalse(condition);
        } catch (AssertionError ae) {
            logger.error(String.format(assertMessage, msg));
        }
    }

    public static void assertFalseHard(String msg, boolean condition){
        String message = String.format(assertMessage, msg);
        try {
            Assert.assertFalse(condition);
        } catch (AssertionError ae) {
            logger.error(String.format(message, msg));
            throw new AssertionError(message);
        }
    }

    public static void assertTrueHard(String msg, boolean condition){
        String message = String.format(assertMessage, msg);
        try {
            Assert.assertTrue(condition);
        } catch (AssertionError ae) {
            logger.error(message);
            throw new AssertionError(message);
        }
    }
}
