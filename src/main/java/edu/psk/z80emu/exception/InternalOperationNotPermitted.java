package edu.psk.z80emu.exception;

/**
 * The InternalOperationNotPermitted is thrown when emulator is trying
 * change unchangable varible, object etc. Is isnt user error, it is internal emulator error.
 */
public class InternalOperationNotPermitted extends RuntimeException {

    public InternalOperationNotPermitted() {
    }

    public InternalOperationNotPermitted(String message) {
        super(message);
    }

    public InternalOperationNotPermitted(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalOperationNotPermitted(Throwable cause) {
        super(cause);
    }

    public InternalOperationNotPermitted(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
