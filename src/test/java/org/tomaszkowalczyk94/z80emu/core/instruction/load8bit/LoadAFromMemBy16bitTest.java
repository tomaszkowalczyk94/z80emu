package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadAFromMemBy16bitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x3A));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x00));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x10)); //ld a, (1000h)

        z80.getMemory().write(0x1000, XBit8.valueOfUnsigned(0x1A));

        z80.runOneInstruction();

        Assert.assertEquals(0x1A, z80.getRegs().getA().getUnsignedValue());
        Assert.assertEquals(3, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(13, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}