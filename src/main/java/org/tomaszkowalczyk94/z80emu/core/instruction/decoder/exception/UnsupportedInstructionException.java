package org.tomaszkowalczyk94.z80emu.core.instruction.decoder.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnsupportedInstructionException extends Z80Exception {

    final XBit8 opcode;

    public UnsupportedInstructionException(XBit8 opcode) {
        super("instruction: "+Integer.toHexString(opcode.getUnsignedValue()));
        this.opcode = opcode;
    }

}
