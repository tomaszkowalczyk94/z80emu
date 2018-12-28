package org.tomaszkowalczyk94.z80emu.core.instruction.compare;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;

public class CompareAndIncrementAndRepeatTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void executeAndRepeat() throws Exception {
        XBit8 valueInRegA = XBit8.valueOfUnsigned(0x80);
        XBit8 valueInMem = XBit8.valueOfUnsigned(0x50);

        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xB1));// CPIR

        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x0010));
        z80.getMem().write(0x0010, valueInMem);

        z80.getRegs().setA(valueInRegA);
        z80.getRegs().setBC(XBit16.valueOfUnsigned(0xFFFF));

        z80.runOneInstruction();

        Assert.assertEquals(0x0011, z80.getRegs().getHL().getUnsignedValue());
        Assert.assertEquals(0xFFFE, z80.getRegs().getBC().getUnsignedValue());

        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.S));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.Z));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.H));
        Assert.assertEquals(true, z80.getRegs().getFlag(PV));
        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.N));

        Assert.assertEquals(0, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(21, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void executeAndStop() throws Exception {
        XBit8 valueInRegA = XBit8.valueOfUnsigned(0xDD);
        XBit8 valueInMem = XBit8.valueOfUnsigned(0xDD);

        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xB1));// CPIR

        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x0010));
        z80.getMem().write(0x0010, valueInMem);

        z80.getRegs().setA(valueInRegA);
        z80.getRegs().setBC(XBit16.valueOfUnsigned(0x0001));

        z80.runOneInstruction();

        Assert.assertEquals(0x0011, z80.getRegs().getHL().getUnsignedValue());
        Assert.assertEquals(0x0000, z80.getRegs().getBC().getUnsignedValue());

        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.S));
        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.Z));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.H));
        Assert.assertEquals(false, z80.getRegs().getFlag(PV));
        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.N));

        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(16, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}