package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class ExchangeAfAfTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0x08)); // EX AF, AF'

        z80.getRegs().setAf(XBit16.valueOfUnsigned(0x12FF));
        z80.getRegs().getAlternativeRegisterSet().setAf(XBit16.valueOfUnsigned(0x5600));

        z80.runOneInstruction();

        Assert.assertEquals(0x5600, z80.getRegs().getAf().getUnsignedValue());
        Assert.assertEquals(0x12FF, z80.getRegs().getAlternativeRegisterSet().getAf().getUnsignedValue());

        Assert.assertEquals(4, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}