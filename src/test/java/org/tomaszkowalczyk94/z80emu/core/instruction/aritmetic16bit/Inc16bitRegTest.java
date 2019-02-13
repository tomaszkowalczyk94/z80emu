package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic16bit;

import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static org.junit.Assert.*;

public class Inc16bitRegTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Z80Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x23)); //inc HL

        z80.getRegs().setHL(XBit16.valueOfUnsigned(400));

        z80.runOneInstruction();

        assertEquals(401, z80.getRegs().getHL().getUnsignedValue());
    }
}