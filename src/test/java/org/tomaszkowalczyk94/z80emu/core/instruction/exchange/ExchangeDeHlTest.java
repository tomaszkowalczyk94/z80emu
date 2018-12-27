package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class ExchangeDeHlTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xEB)); // EX DE, HL

        z80.getRegs().setDE(XBit16.valueOfUnsigned(0x1234));
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x5678));

        z80.runOneInstruction();

        Assert.assertEquals(0x5678, z80.getRegs().getDE().getUnsignedValue());
        Assert.assertEquals(0x1234, z80.getRegs().getHL().getUnsignedValue());

        Assert.assertEquals(4, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}