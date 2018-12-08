package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class LoadRegisterToRegisterTest {

    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getRegisterBank().getRegisterSet().setRegisterB(XBit8.valueOfUnsigned(0xFF));

        z80.getMemory().write(0, XBit8.valueOfUnsigned(0x78)); //LD a,b
        z80.runOneInstruction();

        Assert.assertEquals(0xFF, z80.getRegisterBank().getRegisterSet().getRegisterA().getUnsignedValue());
        Assert.assertEquals(1, z80.getRegisterBank().getRegisterPC().getUnsignedValue());
        Assert.assertEquals(4, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}