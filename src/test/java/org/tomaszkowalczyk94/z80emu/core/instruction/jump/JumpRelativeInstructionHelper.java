package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Assert;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

public class JumpRelativeInstructionHelper {

    public void makeTest(XBit8 opcode, FlagRegManager.Flag flag, boolean flagValue, boolean makeJump) throws Z80Exception {
        Z80 z80 = new Z80();

        z80.getMem().write(0, opcode);
        z80.getMem().write(1, XBit8.valueOfUnsigned(0x7F)); //jr someCondition, 129

        z80.getRegs().setFlag(flag, flagValue);

        z80.runOneInstruction();

        if(makeJump) {
            Assert.assertEquals(0x0081, z80.getRegs().getPc().getUnsignedValue());

            Assert.assertEquals(12, z80.getClockCyclesCounter());
        } else {
            Assert.assertEquals(0x0002, z80.getRegs().getPc().getUnsignedValue());

            Assert.assertEquals(7, z80.getClockCyclesCounter());
            Assert.assertEquals(1, z80.getInstructionCounter());
        }

    }

}
