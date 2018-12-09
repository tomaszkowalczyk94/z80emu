package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadRegisterFromImmediate8bitTest {

    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x0E)); //ld C,n
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0xC9)); //201D

        z80.runOneInstruction();

        Assert.assertEquals(201, z80.getRegisterBank().getRegisterSet().getRegisterC().getUnsignedValue());
        Assert.assertEquals(2, z80.getRegisterBank().getRegisterPC().getUnsignedValue());
        Assert.assertEquals(7, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}