package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic8bit;

import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;

public class CompareAWithMemoryByHlTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Z80Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xBE)); //cp (HL)

        z80.getRegs().setA(XBit8.valueOfUnsigned(0));
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x0031));
        z80.getMem().write(0x0031, XBit8.valueOfUnsigned(10));

        z80.runOneInstruction();

        assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.S));
        assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.Z));
        //assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.H));
        assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.PV));
        assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.N));
        assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.C));
    }
}