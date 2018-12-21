package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static org.junit.Assert.*;

public class RetIfConditionTest {
    @Test
    public void execute() throws Exception {

        // ======= NZ ======
        makeTest(
                XBit8.valueOfUnsigned(0xC0),
                XBit8.valueOfUnsigned(0b00000000), //flag Z - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xC0),
                XBit8.valueOfUnsigned(0b01000000), //flag Z - 1
                false
        );

        // ======= Z ======
        makeTest(
                XBit8.valueOfUnsigned(0xC8),
                XBit8.valueOfUnsigned(0b00000000), //flag Z - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xC8),
                XBit8.valueOfUnsigned(0b01000000), //flag Z - 1
                true
        );

        // ======= NC ======
        makeTest(
                XBit8.valueOfUnsigned(0xD0),
                XBit8.valueOfUnsigned(0b00000000), //flag C - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xD0),
                XBit8.valueOfUnsigned(0b00000001), //flag C - 1
                false
        );

        // ======= C ======
        makeTest(
                XBit8.valueOfUnsigned(0xD8),
                XBit8.valueOfUnsigned(0b00000000), //flag C - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xD8),
                XBit8.valueOfUnsigned(0b00000001), //flag C - 1
                true
        );

        // ======= PO ======
        makeTest(
                XBit8.valueOfUnsigned(0xE0),
                XBit8.valueOfUnsigned(0b00000000), //flag PV - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xE0),
                XBit8.valueOfUnsigned(0b00000100), //flag PV - 1
                false
        );

        // ======= PE ======
        makeTest(
                XBit8.valueOfUnsigned(0xE8),
                XBit8.valueOfUnsigned(0b00000000), //flag PV - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xE8),
                XBit8.valueOfUnsigned(0b00000100), //flag PV - 1
                true
        );

        // ======= P ======
        makeTest(
                XBit8.valueOfUnsigned(0xF0),
                XBit8.valueOfUnsigned(0b00000000), //flag S - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xF0),
                XBit8.valueOfUnsigned(0b10000100), //flag S - 1
                false
        );

        // ======= M ======
        makeTest(
                XBit8.valueOfUnsigned(0xF8),
                XBit8.valueOfUnsigned(0b00000000), //flag S - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xF8),
                XBit8.valueOfUnsigned(0b10000100), //flag S - 1
                true
        );
    }

    protected void makeTest(XBit8 opcode, XBit8 flagRegister, boolean predictedMakeRet) throws Z80Exception {
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