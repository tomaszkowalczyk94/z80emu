package org.tomaszkowalczyk94.z80emu.core;

/**
 * Main exception type for project
 */
public abstract class Z80Exception extends Exception {
    protected Z80Exception() {
    }

    public Z80Exception(String message) {
        super(message);
    }

    public Z80Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Z80Exception(Throwable cause) {
        super(cause);
    }

    public Z80Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
