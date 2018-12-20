package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class RetTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xC9)); //ret

        z80.getMem().write(0xFFFD, XBit8.valueOfUnsigned(0xBB));
        z80.getMem().write(0xFFFE, XBit8.valueOfUnsigned(0xAA));

        z80.getRegs().setSp(XBit16.valueOfUnsigned(0xFFFD));

        z80.runOneInstruction();

        Assert.assertEquals(0xAABB, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(0xFFFF, z80.getRegs().getSp().getUnsignedValue());

        Assert.assertEquals(10, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}