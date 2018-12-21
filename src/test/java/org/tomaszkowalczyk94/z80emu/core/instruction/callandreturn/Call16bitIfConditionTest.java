package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.junit.Assert;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;

import static org.junit.Assert.*;

public class Call16bitIfConditionTest {



    @Test
    public void execute() throws Exception {

        // ======= NZ ======
        makeTest(
                XBit8.valueOfUnsigned(0xC4),
                XBit8.valueOfUnsigned(0b00000000), //flag Z - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xC4),
                XBit8.valueOfUnsigned(0b01000000), //flag Z - 1
                false
        );

        // ======= Z ======
        makeTest(
                XBit8.valueOfUnsigned(0xCC),
                XBit8.valueOfUnsigned(0b00000000), //flag Z - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xCC),
                XBit8.valueOfUnsigned(0b01000000), //flag Z - 1
                true
        );

        // ======= NC ======
        makeTest(
                XBit8.valueOfUnsigned(0xD4),
                XBit8.valueOfUnsigned(0b00000000), //flag C - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xD4),
                XBit8.valueOfUnsigned(0b00000001), //flag C - 1
                false
        );

        // ======= C ======
        makeTest(
                XBit8.valueOfUnsigned(0xDC),
                XBit8.valueOfUnsigned(0b00000000), //flag C - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xDC),
                XBit8.valueOfUnsigned(0b00000001), //flag C - 1
                true
        );

        // ======= PO ======
        makeTest(
                XBit8.valueOfUnsigned(0xE4),
                XBit8.valueOfUnsigned(0b00000000), //flag PV - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xE4),
                XBit8.valueOfUnsigned(0b00000100), //flag PV - 1
                false
        );

        // ======= PE ======
        makeTest(
                XBit8.valueOfUnsigned(0xEC),
                XBit8.valueOfUnsigned(0b00000000), //flag PV - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xEC),
                XBit8.valueOfUnsigned(0b00000100), //flag PV - 1
                true
        );

        // ======= P ======
        makeTest(
                XBit8.valueOfUnsigned(0xF4),
                XBit8.valueOfUnsigned(0b00000000), //flag S - 0
                true
        );

        makeTest(
                XBit8.valueOfUnsigned(0xF4),
                XBit8.valueOfUnsigned(0b10000100), //flag S - 1
                false
        );

        // ======= M ======
        makeTest(
                XBit8.valueOfUnsigned(0xFC),
                XBit8.valueOfUnsigned(0b00000000), //flag S - 0
                false
        );

        makeTest(
                XBit8.valueOfUnsigned(0xFC),
                XBit8.valueOfUnsigned(0b10000100), //flag S - 1
                true
        );
    }

    protected void makeTest(XBit8 opcode, XBit8 flagRegister, boolean predictedMakeCall) throws Z80Exception {
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