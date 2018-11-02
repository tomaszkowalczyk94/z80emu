package edu.psk.z80emu.exception;

public class InternalLogicError extends RuntimeException {
    public InternalLogicError() {
    }

    public InternalLogicError(String message) {
        super(message);
    }

    public InternalLogicError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalLogicError(Throwable cause) {
        super(cause);
    }

    public InternalLogicError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
