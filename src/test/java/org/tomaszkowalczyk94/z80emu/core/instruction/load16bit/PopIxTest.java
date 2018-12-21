package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class PopIxTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xDD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0xE1)); //pop IX

        z80.getRegs().setSp(XBit16.valueOfUnsigned(0xFFFD));
        z80.getMem().write(0xFFFD, XBit8.valueOfUnsigned(0xBB));
        z80.getMem().write(0xFFFE, XBit8.valueOfUnsigned(0xAA));

        z80.runOneInstruction();

        Assert.assertEquals(0xAABB, z80.getRegs().getIx().getUnsignedValue());
        Assert.assertEquals(0xFFFF, z80.getRegs().getSp().getUnsignedValue());
        Assert.assertEquals(1, z80.getRegs().getPc().getUnsignedValue());


        Assert.assertEquals(14, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}