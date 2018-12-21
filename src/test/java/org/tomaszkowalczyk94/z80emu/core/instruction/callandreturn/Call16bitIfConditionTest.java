package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.ConditionInstructionHelper;

import static org.junit.Assert.*;

public class Call16bitIfConditionTest {



    @Test
    public void execute() throws Exception {

        ConditionInstructionHelper conditionInstructionHelper = new ConditionInstructionHelper();

        conditionInstructionHelper.makeTest(
                0b11,
                0b100,
                this::makeTest

        );
    }

    private void makeTest(XBit8 opcode, XBit8 flagRegister, boolean predictedMakeCall) throws Z80Exception {
        Z80 z80 = new Z80();

        z80.getMemory().write(0, opcode);
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x00));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x01)); //call cc, 100h

        z80.getRegs().setF(flagRegister);

        z80.runOneInstruction();

        if(predictedMakeCall) {
            Assert.assertEquals(0x100, z80.getRegs().getPc().getUnsignedValue());
            Assert.assertEquals(0x03, z80.getMem().read(0xFFFD).getUnsignedValue());
            Assert.assertEquals(0x00, z80.getMem().read(0xFFFE).getUnsignedValue());
            Assert.assertEquals(0xFFFD, z80.getRegs().getSp().getUnsignedValue());

            Assert.assertEquals(17, z80.getClockCyclesCounter());
            Assert.assertEquals(1, z80.getInstructionCounter());
        } else {
            Assert.assertEquals(0x0003, z80.getRegs().getPc().getUnsignedValue());
            Assert.assertEquals(0xFFFF, z80.getRegs().getSp().getUnsignedValue());

            Assert.assertEquals(10, z80.getClockCyclesCounter());
            Assert.assertEquals(1, z80.getInstructionCounter());
        }
    }

}