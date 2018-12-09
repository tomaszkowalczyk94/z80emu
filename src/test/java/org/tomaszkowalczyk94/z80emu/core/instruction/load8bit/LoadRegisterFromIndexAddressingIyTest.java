package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadRegisterFromIndexAddressingIyTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xFD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x7E));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x7F)); //ld a,(IX +127)

        z80.getMemory().write(0x107F, XBit8.valueOfUnsigned(0xAA));

        z80.getRegs().setIy(XBit16.valueOfUnsigned(0x1000));

        z80.runOneInstruction();

        Assert.assertEquals(0xAA, z80.getRegisterBank().getA().getUnsignedValue());
        Assert.assertEquals(3, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(19, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }

    @Test
    public void execute2() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xFD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x7E));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x7F)); //ld a,(IX +127)

        z80.getMemory().write(0xF07F, XBit8.valueOfUnsigned(0xAA));

        z80.getRegs().setIy(XBit16.valueOfUnsigned(0xF000));

        z80.runOneInstruction();

        Assert.assertEquals(0xAA, z80.getRegisterBank().getA().getUnsignedValue());
        Assert.assertEquals(3, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(19, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}