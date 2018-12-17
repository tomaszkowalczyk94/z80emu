package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadMemBy16bitFromRegTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x53));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x00));
        z80.getMemory().write(3, XBit8.valueOfUnsigned(0x02)); // ld (200h), DE

        z80.getRegs().setDE(XBit16.valueOfUnsigned(0x1ABF));

        z80.runOneInstruction();

        Assert.assertEquals(0xBF, z80.getMem().read(0x200).getUnsignedValue());
        Assert.assertEquals(0x1A, z80.getMem().read(0x201).getUnsignedValue());

        Assert.assertEquals(4, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(20, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}