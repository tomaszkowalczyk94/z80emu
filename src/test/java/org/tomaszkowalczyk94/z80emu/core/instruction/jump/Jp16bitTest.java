package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class Jp16bitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xC3));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xDD));
        z80.getMem().write(2, XBit8.valueOfUnsigned(0xCC)); //jp 0cccch

        z80.getRegs().setSp(XBit16.valueOfUnsigned(0xFFFD));

        z80.runOneInstruction();

        Assert.assertEquals(0xCCDD, z80.getRegs().getPc().getUnsignedValue());

        Assert.assertEquals(10, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}