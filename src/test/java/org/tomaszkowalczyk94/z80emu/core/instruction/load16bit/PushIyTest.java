package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class PushIyTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xFD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0xE5));//push iy
        z80.getRegs().setIy(XBit16.valueOfUnsigned(0xAABB));

        z80.runOneInstruction();

        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(0xBB, z80.getMem().read(0xFFFD).getUnsignedValue());
        Assert.assertEquals(0xAA, z80.getMem().read(0xFFFE).getUnsignedValue());
        Assert.assertEquals(0xFFFD, z80.getRegs().getSp().getUnsignedValue());

        Assert.assertEquals(15, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}