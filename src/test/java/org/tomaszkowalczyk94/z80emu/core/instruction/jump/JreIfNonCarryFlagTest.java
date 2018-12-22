package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.junit.Assert.*;

public class JreIfNonCarryFlagTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        JumpRelativeInstructionHelper jumpRelativeInstructionHelper = new JumpRelativeInstructionHelper();

        jumpRelativeInstructionHelper.makeTest(
                XBit8.valueOfUnsigned(0x30),
                FlagRegManager.Flag.C,
                true,
                false
        );

        jumpRelativeInstructionHelper.makeTest(
                XBit8.valueOfUnsigned(0x30),
                FlagRegManager.Flag.C,
                false,
                true
        );

    }
}