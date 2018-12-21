package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.ConditionInstructionHelper;

import static org.junit.Assert.*;

public class RetIfConditionTest {
    @Test
    public void execute() throws Exception {

        ConditionInstructionHelper conditionInstructionHelper = new ConditionInstructionHelper();

        conditionInstructionHelper.makeTest(
                0b11,
                0b000,
                this::makeTest

        );
    }

    private void makeTest(XBit8 opcode, XBit8 flagRegister, boolean predictedMakeRet) throws Z80Exception {
        Z80 z80 = new Z80();

        z80.getMemory().write(0, opcode);

        z80.getMem().write(0xFFFD, XBit8.valueOfUnsigned(0xBB));
        z80.getMem().write(0xFFFE, XBit8.valueOfUnsigned(0xAA));
        z80.getRegs().setSp(XBit16.valueOfUnsigned(0xFFFD));

        z80.getRegs().setF(flagRegister);

        z80.runOneInstruction();

        if(predictedMakeRet) {
            Assert.assertEquals(0xAABB, z80.getRegs().getPc().getUnsignedValue());
            Assert.assertEquals(0xFFFF, z80.getRegs().getSp().getUnsignedValue());

            Assert.assertEquals(11, z80.getClockCyclesCounter());
            Assert.assertEquals(1, z80.getInstructionCounter());
        } else {
            Assert.assertEquals(0x0001, z80.getRegs().getPc().getUnsignedValue());
            Assert.assertEquals(0xFFFD, z80.getRegs().getSp().getUnsignedValue());

            Assert.assertEquals(5, z80.getClockCyclesCounter());
            Assert.assertEquals(1, z80.getInstructionCounter());
        }


    }
}