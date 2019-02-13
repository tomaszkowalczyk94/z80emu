package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic8bit;

import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import static org.junit.Assert.*;

public class Dec8bitRegTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Z80Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x05)); //dec b

        z80.getRegs().setB(XBit8.valueOfUnsigned(10));

        z80.runOneInstruction();

        assertEquals(9, z80.getRegs().getB().getUnsignedValue());
    }
}