package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadRegFromMemBy16BitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x4B));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x00));
        z80.getMemory().write(3, XBit8.valueOfUnsigned(0x01)); // ld BC, (100h)

        z80.getMem().write(0x100, XBit8.valueOfUnsigned(0xBB));
        z80.getMem().write(0x101, XBit8.valueOfUnsigned(0xAA));

        z80.runOneInstruction();

        Assert.assertEquals(0xAABB, z80.getRegs().getBC().getUnsignedValue());

        Assert.assertEquals(4, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(20, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}