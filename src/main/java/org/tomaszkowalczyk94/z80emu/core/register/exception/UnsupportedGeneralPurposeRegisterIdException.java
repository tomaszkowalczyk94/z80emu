package org.tomaszkowalczyk94.z80emu.core.register.exception;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.register.DuplicableRegisterSet;
/**
 * Exception thrown when {@link DuplicableRegisterSet#set8BitRegisterById(byte, XBit8)} method parameter is invalid
 */
public class UnsupportedGeneralPurposeRegisterIdException extends Exception {
    final byte registerId;

    public UnsupportedGeneralPurposeRegisterIdException(byte registerId) {
        super("registerId: "+registerId);
        this.registerId = registerId;
    }
}
