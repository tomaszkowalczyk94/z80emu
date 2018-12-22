package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class DecrementAndJumpOnNonZeroTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0x10));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0x62));// djnz 100

        z80.getRegs().setB(XBit8.valueOfUnsigned(0));

        z80.runOneInstruction();

        Assert.assertEquals(0x0064, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(0xFF, z80.getRegs().getB().getUnsignedValue());

        Assert.assertEquals(13, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void execute2() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0x10));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0x62));// djnz 100

        z80.getRegs().setB(XBit8.valueOfUnsigned(1));

        z80.runOneInstruction();

        Assert.assertEquals(0x0002, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(0, z80.getRegs().getB().getUnsignedValue());

        Assert.assertEquals(8, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}