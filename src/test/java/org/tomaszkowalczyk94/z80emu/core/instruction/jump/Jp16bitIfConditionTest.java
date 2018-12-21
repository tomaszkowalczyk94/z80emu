package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.junit.Assert;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.ConditionInstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import static org.junit.Assert.*;

public class Jp16bitIfConditionTest{
    

    @Test
    public void execute() throws Exception {

        ConditionInstructionHelper conditionInstructionHelper = new ConditionInstructionHelper();

        conditionInstructionHelper.makeTest(0b11, 0b010,
                (XBit8 opcode, XBit8 flagRegister, boolean predictedIsMake) -> {

            Z80 z80 = new Z80();

            z80.getMemory().write(0, opcode);
            z80.getMemory().write(1, XBit8.valueOfUnsigned(0xDD));
            z80.getMemory().write(2, XBit8.valueOfUnsigned(0xCC)); //jp cc, ccddh

            z80.getRegs().setF(flagRegister);

            z80.runOneInstruction();

            if(predictedIsMake) {
                Assert.assertEquals(0xCCDD, z80.getRegs().getPc().getUnsignedValue());
            } else {
                Assert.assertEquals(0x0003, z80.getRegs().getPc().getUnsignedValue());
            }

            Assert.assertEquals(10, z80.getClockCyclesCounter());

        });
    }

}