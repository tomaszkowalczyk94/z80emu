package org.tomaszkowalczyk94.z80emu.core.register.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.register.DuplicableRegisterSet;

/**
 * Exception thrown when {@link DuplicableRegisterSet#set8BitRegisterById(byte, XBit8)} method parameter is invalid
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UnsupportedGeneralPurposeRegisterIdException extends Z80Exception {
    final Byte registerId;

    public UnsupportedGeneralPurposeRegisterIdException(byte registerId) {
        super("registerId: "+registerId);
        this.registerId = registerId;
    }
}
