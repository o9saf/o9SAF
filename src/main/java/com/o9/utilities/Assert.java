package com.o9.utilities;

public class Assert {

    /**
    * Fails a test with the given message.
    * @param message the assertion error message
    */
    public static void fail(String message) {
        throw new AssertionError(message);
    }

    /**
     * Fails a test with no message.
     */
    static public void fail() {
        fail(null);
    }
}
