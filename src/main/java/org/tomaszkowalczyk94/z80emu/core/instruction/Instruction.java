package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * Representation of one cpu instruction
 */
public abstract class Instruction {

    protected InstructionHelper helper;

    public Instruction(InstructionHelper helper) {
        this.helper = helper;
    }

    public abstract InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception;
}
