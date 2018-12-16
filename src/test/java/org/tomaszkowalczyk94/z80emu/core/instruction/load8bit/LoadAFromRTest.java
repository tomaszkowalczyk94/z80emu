package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;

public class LoadAFromRTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    private void prepareZ80(XBit8 regR) throws MemoryException {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xED));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x5F)); //ld A,I

        z80.getRegs().setF(XBit8.valueOfUnsigned(0xFF));

        z80.setIff2(true);

        z80.getRegs().setI(regR);
    }

    @Test
    public void execute() throws Exception {
        prepareZ80(XBit8.valueOfSigned(0x44));
        z80.runOneInstruction();

        Assert.assertEquals(0x44, z80.getRegs().getA().getSignedValue());

        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.S));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.Z));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.H));
        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.PV));
        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.N));

        Assert.assertEquals(2, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(9, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void testFlags1() throws Exception {
        prepareZ80(XBit8.valueOfSigned(-40));
        z80.runOneInstruction();

        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.S));
    }

    @Test
    public void testFlags2() throws Exception {
        prepareZ80(XBit8.valueOfSigned(0));
        z80.runOneInstruction();

        Assert.assertEquals(false, z80.getRegs().getFlag(FlagRegManager.Flag.S));
        Assert.assertEquals(true, z80.getRegs().getFlag(FlagRegManager.Flag.Z));
    }
}