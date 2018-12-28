package org.tomaszkowalczyk94.z80emu.core.instruction.compare;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;

public class CompareAndIncrementTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        XBit8 valueInRegA = XBit8.valueOfUnsigned(0x80);
        XBit8 valueInMem = XBit8.valueOfUnsigned(0x50);

        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xA1));// CPI

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

        Assert.assertEquals(2, z80.getRegs().getPc().getUnsignedValue());
        Assert.assertEquals(16, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void testFlags() throws Exception {
        //sign flag
        makeTestFor(new Z80(), XBit8.valueOfUnsigned(0x90), XBit8.valueOfUnsigned(0x50), FlagRegManager.Flag.S, false);
        makeTestFor(new Z80(), XBit8.valueOfSigned(0x5), XBit8.valueOfSigned(0x10), FlagRegManager.Flag.S, true);
        makeTestFor(new Z80(), XBit8.valueOfSigned(127), XBit8.valueOfSigned(127), FlagRegManager.Flag.S, false);
        makeTestFor(new Z80(), XBit8.valueOfSigned(-128), XBit8.valueOfSigned(-128), FlagRegManager.Flag.S, false);
        makeTestFor(new Z80(), XBit8.valueOfSigned(0), XBit8.valueOfSigned(0), FlagRegManager.Flag.S, false);

        //zero flag
        makeTestFor(new Z80(), XBit8.valueOfSigned(10), XBit8.valueOfSigned(10), FlagRegManager.Flag.Z, true);
        makeTestFor(new Z80(), XBit8.valueOfSigned(10), XBit8.valueOfSigned(0), FlagRegManager.Flag.Z, false);
        makeTestFor(new Z80(), XBit8.valueOfSigned(-128), XBit8.valueOfSigned(-128), FlagRegManager.Flag.Z, true);

        //half carry flag
        makeTestFor(new Z80(), XBit8.valueOfSigned(5), XBit8.valueOfSigned(16), FlagRegManager.Flag.H, false);
        makeTestFor(new Z80(), XBit8.valueOfSigned(5), XBit8.valueOfSigned(6), FlagRegManager.Flag.H, true);

        //P/V flag
        makeTestFor(new Z80(), XBit8.valueOfSigned(5), XBit8.valueOfSigned(6), PV, true);

        //N
        makeTestFor(new Z80(), XBit8.valueOfSigned(5), XBit8.valueOfSigned(6), FlagRegManager.Flag.N, true);
    }

    @Test
    public void testPv() throws Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xA1));// CPI

        z80.getRegs().setFlag(PV, true);
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x0010));
        z80.getMem().write(0x0010, XBit8.valueOfSigned(5));

        z80.getRegs().setA(XBit8.valueOfSigned(5));
        z80.getRegs().setBC(XBit16.valueOfUnsigned(0x0001));

        z80.runOneInstruction();

        Assert.assertEquals(0x0011, z80.getRegs().getHL().getUnsignedValue());
        Assert.assertEquals(0x0000, z80.getRegs().getBC().getUnsignedValue());

        Assert.assertEquals(false, z80.getRegs().getFlag(PV));
    }

    private void makeTestFor(Z80 z80, XBit8 accumulator, XBit8 memValue, FlagRegManager.Flag flag, boolean expectedFlagValue) throws Z80Exception {
        z80.getMem().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMem().write(1, XBit8.valueOfUnsigned(0xA1));// CPI

        z80.getRegs().setFlag(flag, !expectedFlagValue);
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x0010));
        z80.getMem().write(0x0010, memValue);

        z80.getRegs().setA(accumulator);
        z80.getRegs().setBC(XBit16.valueOfUnsigned(0xFFFF));

        z80.runOneInstruction();

        Assert.assertEquals(0x0011, z80.getRegs().getHL().getUnsignedValue());
        Assert.assertEquals(0xFFFE, z80.getRegs().getBC().getUnsignedValue());

        Assert.assertEquals(expectedFlagValue, z80.getRegs().getFlag(flag));
    }
}