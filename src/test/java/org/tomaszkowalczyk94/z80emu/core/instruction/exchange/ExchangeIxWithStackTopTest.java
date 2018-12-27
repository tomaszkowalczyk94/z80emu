package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class ExchangeIxWithStackTopTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xDD));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xE3)); // EX (SP), IX

        z80.getMem().write(0xFFFD, XBit8.valueOfUnsigned(0x11));
        z80.getMem().write(0xFFFE, XBit8.valueOfUnsigned(0x22));

        z80.getRegs().setIx(XBit16.valueOfUnsigned(0xAABB));
        z80.getRegs().setSp(XBit16.valueOfUnsigned(0xFFFD));

        z80.runOneInstruction();

        Assert.assertEquals(0x2211, z80.getRegs().getIx().getUnsignedValue());
        Assert.assertEquals(0xBB, z80.getMem().read(0xFFFD).getUnsignedValue());
        Assert.assertEquals(0xAA, z80.getMem().read(0xFFFE).getUnsignedValue());

        Assert.assertEquals(23, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}