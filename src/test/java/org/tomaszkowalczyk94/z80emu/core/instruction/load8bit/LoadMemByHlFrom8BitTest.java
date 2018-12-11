package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;


public class LoadMemByHlFrom8BitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x36));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0xFA)); //ld (hl), 250

        z80.getRegs().setHL(XBit16.valueOfUnsigned(0xAAAA));

        z80.runOneInstruction();

        Assert.assertEquals(0xFA, z80.getMemory().read(XBit16.valueOfUnsigned(0xAAAA)).getUnsignedValue());
        Assert.assertEquals(2, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(10, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}