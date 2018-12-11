package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadMemByHlFromRegTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x72)); //ld (HL), D
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0xFFFF));
        z80.getRegs().setD(XBit8.valueOfUnsigned(0xAB));

        z80.runOneInstruction();

        Assert.assertEquals(0xAB, z80.getMemory().read(XBit16.valueOfUnsigned(0xFFFF)).getUnsignedValue());
        Assert.assertEquals(1, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(7, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}