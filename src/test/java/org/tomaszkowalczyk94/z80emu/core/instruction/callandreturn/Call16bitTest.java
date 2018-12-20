package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class Call16bitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xCD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x00));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x01)); //call 100h


        z80.runOneInstruction();

        Assert.assertEquals(0x100, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(0x03, z80.getMem().read(0xFFFD).getUnsignedValue());
        Assert.assertEquals(0x00, z80.getMem().read(0xFFFE).getUnsignedValue());
        Assert.assertEquals(0xFFFD, z80.getRegs().getSp().getUnsignedValue());

        Assert.assertEquals(17, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}