package org.tomaszkowalczyk94.z80emu.core.memory.exception;

import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

/**
 * Oznacza problem z pamięcią, najczęściej wyrzucany gdy próbujemy odwołać się do adresu
 * pamięci który nie istnieje.
 */
public class MemoryException extends Z80Exception{

    public MemoryException() {
    }

    public MemoryException(String message) {
        super(message);
    }

    public MemoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemoryException(Throwable cause) {
        super(cause);
    }

    public MemoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
