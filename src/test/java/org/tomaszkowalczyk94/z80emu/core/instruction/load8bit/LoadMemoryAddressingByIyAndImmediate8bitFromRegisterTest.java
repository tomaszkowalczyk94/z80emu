package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadMemoryAddressingByIyAndImmediate8bitFromRegisterTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xFD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x77));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x9C)); // ld (ix -100), a

        z80.getRegs().setIy(XBit16.valueOfUnsigned(0xFFFF));
        z80.getRegs().setA(XBit8.valueOfUnsigned(0xAB));

        z80.runOneInstruction();

        Assert.assertEquals(0xAB, z80.getMem().read(XBit16.valueOfUnsigned(65435)).getUnsignedValue());

        Assert.assertEquals(3, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(19, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}