package org.tomaszkowalczyk94.z80emu.core.instruction.inout;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

public class InputDataToATest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xDB));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xC8));// IN A, (200)

        z80.getIo().write(
                XBit16.valueOfUnsigned(0xFFC8),
                XBit8.valueOfUnsigned(0x01)
        );

        z80.runOneInstruction();

        Assert.assertEquals(1, z80.getRegs().getA().getUnsignedValue());

        Assert.assertEquals(11, z80.getClockCyclesCounter());
        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
    }
}