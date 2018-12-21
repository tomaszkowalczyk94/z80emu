package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import java.util.function.Consumer;

public class ConditionInstructionHelper {

    @FunctionalInterface
    public interface ConsumerTest {
        void accept(XBit8 opcode, XBit8 flagRegister, boolean predictedIsMake) throws Z80Exception;
    }

    public void makeTest(int opCodePrefix, int opCodeSuffix, ConsumerTest consumerTest ) throws Z80Exception {

        XBit8 opcode;

        //NZ
        opcode = buildOpcode(opCodePrefix, 0b000, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b10111111), true); //flag Z - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b01000000), false); //flag Z - 1

        //Z
        opcode = buildOpcode(opCodePrefix, 0b001, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), false); //flag Z - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b01000000), true);  //flag Z - 1

        //NC
        opcode = buildOpcode(opCodePrefix, 0b010, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), true);  //flag C - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000001), false);  //flag C - 1

        //C
        opcode = buildOpcode(opCodePrefix, 0b011, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), false);  //flag C - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000001), true);  //flag C - 1

        //PO
        opcode = buildOpcode(opCodePrefix, 0b100, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), true);  //flag PV - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000100), false);  //flag PV - 1

        //PE
        opcode = buildOpcode(opCodePrefix, 0b101, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), false);  //flag PV - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000100), true);  //flag PV - 1

        //P
        opcode = buildOpcode(opCodePrefix, 0b110, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), true);  //flag S - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b10000000), false);  //flag S - 1

        //M
        opcode = buildOpcode(opCodePrefix, 0b111, opCodeSuffix);
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b00000000), false);  //flag S - 0
        consumerTest.accept(opcode,XBit8.valueOfUnsigned(0b10000100), true);  //flag S - 1
    }

    private XBit8 buildOpcode(int prefixParam,int conditionIdParam, int suffixParam) {

        XBit8 prefix = XBit8.valueOfUnsigned(prefixParam);
        XBit8 conditionId = XBit8.valueOfUnsigned(conditionIdParam);
        XBit8 suffix = XBit8.valueOfUnsigned(suffixParam);

        return XBit8.valueOfBooleanArray(new boolean[]{
                prefix.getBit(1),
                prefix.getBit(0),
                conditionId.getBit(2),
                conditionId.getBit(1),
                conditionId.getBit(0),
                suffix.getBit(2),
                suffix.getBit(1),
                suffix.getBit(0),
        });
    }

}
