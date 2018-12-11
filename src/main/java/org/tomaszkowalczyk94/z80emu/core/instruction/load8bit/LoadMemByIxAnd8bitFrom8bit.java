package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

public class LoadMemByIxAnd8bitFrom8bit implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {

    }

    @Override
    public int getMachineCycles() {
        return 0;
    }

    @Override
    public int getClocks() {
        return 0;
    }

    @Override
    public float getExecutionTime() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
