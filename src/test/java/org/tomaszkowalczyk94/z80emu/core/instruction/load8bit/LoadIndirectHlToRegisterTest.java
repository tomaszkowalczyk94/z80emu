package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadIndirectHlToRegisterTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x5E)); //ld E,(HL)
        z80.getMemory().write(9, XBit8.valueOfUnsigned(0xFF));
        z80.getRegs().setHL(XBit16.valueOfUnsigned(9));

        z80.runOneInstruction();

        Assert.assertEquals(0xFF, z80.getRegisterBank().getRegSet().getE().getUnsignedValue());
        Assert.assertEquals(1, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(7, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}