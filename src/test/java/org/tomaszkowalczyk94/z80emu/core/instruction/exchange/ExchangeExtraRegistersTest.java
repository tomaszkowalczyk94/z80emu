package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class ExchangeExtraRegistersTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xD9)); // EXX

        z80.getRegs().setAf(XBit16.valueOfUnsigned(0x1111));
        z80.getRegs().setBC(XBit16.valueOfUnsigned(0x2222));
        z80.getRegs().setDE(XBit16.valueOfUnsigned(0x3333));
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x4444));

        z80.getRegs().getAlternativeRegisterSet().setAf(XBit16.valueOfUnsigned(0x5555));
        z80.getRegs().getAlternativeRegisterSet().setBC(XBit16.valueOfUnsigned(0x6666));
        z80.getRegs().getAlternativeRegisterSet().setDE(XBit16.valueOfUnsigned(0x7777));
        z80.getRegs().getAlternativeRegisterSet().setHL(XBit16.valueOfUnsigned(0x8888));

        z80.runOneInstruction();

        Assert.assertEquals(0x1111, z80.getRegs().getAf().getUnsignedValue());
        Assert.assertEquals(0x6666, z80.getRegs().getBC().getUnsignedValue());
        Assert.assertEquals(0x7777, z80.getRegs().getDE().getUnsignedValue());
        Assert.assertEquals(0x8888, z80.getRegs().getHL().getUnsignedValue());

        Assert.assertEquals(0x5555, z80.getRegs().getAlternativeRegisterSet().getAf().getUnsignedValue());
        Assert.assertEquals(0x2222, z80.getRegs().getAlternativeRegisterSet().getBC().getUnsignedValue());
        Assert.assertEquals(0x3333, z80.getRegs().getAlternativeRegisterSet().getDE().getUnsignedValue());
        Assert.assertEquals(0x4444, z80.getRegs().getAlternativeRegisterSet().getHL().getUnsignedValue());

        Assert.assertEquals(4, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}