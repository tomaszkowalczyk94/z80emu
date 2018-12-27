package org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;

public class LoadDataAndIncrementTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xA0)); // LDI

        z80.getRegs().setHL(XBit16.valueOfUnsigned(0xA000));
        z80.getMem().write(0xA000, XBit8.valueOfUnsigned(0xBC));

        z80.getRegs().setDE(XBit16.valueOfUnsigned(0xB000));

        z80.getRegs().setBC(XBit16.valueOfUnsigned(0xFFFF));

        z80.getRegs().setFlag(FlagRegManager.Flag.H, true);
        z80.getRegs().setFlag(FlagRegManager.Flag.N, true);
        z80.getRegs().setFlag(FlagRegManager.Flag.PV, false);

        z80.runOneInstruction();

        Assert.assertEquals(0xBC, z80.getMem().read(0xA000).getUnsignedValue());
        Assert.assertEquals(0xBC, z80.getMem().read(0xB000).getUnsignedValue());

        Assert.assertEquals(0xB001, z80.getRegs().getDE().getUnsignedValue());
        Assert.assertEquals(0xA001, z80.getRegs().getHL().getUnsignedValue());

        Assert.assertEquals(0xFFFE, z80.getRegs().getBC().getUnsignedValue());

        //flags
        Assert.assertFalse(z80.getRegs().getFlag(FlagRegManager.Flag.H));
        Assert.assertFalse(z80.getRegs().getFlag(FlagRegManager.Flag.N));
        Assert.assertTrue(z80.getRegs().getFlag(FlagRegManager.Flag.PV));

        Assert.assertEquals(16, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
    }

    @Test
    public void executeWithPvFlagShouldBeFalse() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xA0)); // LDI

        z80.getRegs().setHL(XBit16.valueOfUnsigned(0xA000));
        z80.getMem().write(0xA000, XBit8.valueOfUnsigned(0xBC));

        z80.getRegs().setDE(XBit16.valueOfUnsigned(0xB000));

        z80.getRegs().setBC(XBit16.valueOfUnsigned(0x0001));

        z80.getRegs().setFlag(FlagRegManager.Flag.H, true);
        z80.getRegs().setFlag(FlagRegManager.Flag.N, true);
        z80.getRegs().setFlag(FlagRegManager.Flag.PV, true);

        z80.runOneInstruction();

        Assert.assertEquals(0xBC, z80.getMem().read(0xA000).getUnsignedValue());
        Assert.assertEquals(0xBC, z80.getMem().read(0xB000).getUnsignedValue());

        Assert.assertEquals(0xB001, z80.getRegs().getDE().getUnsignedValue());
        Assert.assertEquals(0xA001, z80.getRegs().getHL().getUnsignedValue());

        Assert.assertEquals(0x0000, z80.getRegs().getBC().getUnsignedValue());

        //flags
        Assert.assertFalse(z80.getRegs().getFlag(FlagRegManager.Flag.H));
        Assert.assertFalse(z80.getRegs().getFlag(FlagRegManager.Flag.N));
        Assert.assertFalse(z80.getRegs().getFlag(FlagRegManager.Flag.PV));

        Assert.assertEquals(16, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
    }
}