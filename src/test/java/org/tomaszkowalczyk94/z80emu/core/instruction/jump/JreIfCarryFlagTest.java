package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;

public class JreIfCarryFlagTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0x38));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0x7F)); //jr C, 129

        z80.getRegs().setFlag(FlagRegManager.Flag.C, true);

        z80.runOneInstruction();

        Assert.assertEquals(0x0081, z80.getRegs().getPc().getUnsignedValue());

        Assert.assertEquals(12, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void execute2() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0x38));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0x7F)); //jr C, 129

        z80.getRegs().setFlag(FlagRegManager.Flag.C, false);

        z80.runOneInstruction();

        Assert.assertEquals(0x0002, z80.getRegs().getPc().getUnsignedValue());

        Assert.assertEquals(7, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}