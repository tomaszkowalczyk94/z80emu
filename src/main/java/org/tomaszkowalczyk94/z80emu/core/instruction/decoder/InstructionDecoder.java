package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.decoder.exception.UnsupportedInstructionException;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class InstructionDecoder {

    private final InstructionsContainer instructionsContainer = new InstructionsContainer();

    public Instruction decodeOneByte(XBit8 instructionByte) throws MemoryException, UnsupportedInstructionException {
        Memory memory = new Memory();
        memory.write(0, instructionByte);
        return decode(memory, XBit16.valueOfUnsigned(0));
    }

    public Instruction decode(Memory memory, XBit16 pc) throws UnsupportedInstructionException, MemoryException {
        XBit8 opcode = memory.read(pc);

        int opcodeUnsignedValue = opcode.getUnsignedValue();
        XBit8 secondByte = readSecondByte(memory, pc);

        switch (opcodeUnsignedValue) {
            case 0xDD:
                return decodeDdOpcode(opcode, secondByte);
            case 0xFD:
                return decodeFdOpcode(opcode, secondByte);
            case 0xED:
                return decodeEdOpcode(opcode, secondByte);
            case 0x36:
                return instructionsContainer.loadMemByHlFrom8Bit;
            case 0x0A:
                return instructionsContainer.loadAFromMemByBc;
            case 0x1A:
                return instructionsContainer.loadAFromMemByDe;
            case 0x3A:
                return instructionsContainer.loadAFromMemBy16bit;
            case 0x02:
                return instructionsContainer.loadMemoryByBcFromA;
            case 0x12:
                return instructionsContainer.loadMemoryByDeFromA;
            case 0x32:
                return instructionsContainer.loadMemoryBy16BitFromA;
            case 0x2A:
                return instructionsContainer.loadHlFromMemBy16bit;
            case 0x22:
                return instructionsContainer.loadMemBy16bitFromHl;
            case 0xF9:
                return instructionsContainer.loadSpFromHl;
            case 0xCD:
                return instructionsContainer.call16bit;
            case 0xC9:
                return instructionsContainer.ret;
            case 0xC3:
                return instructionsContainer.jp16bit;
            case 0x18:
                return instructionsContainer.jre;
            case 0x38:
                return instructionsContainer.jreIfCarryFlag;
            case 0x30:
                return instructionsContainer.jreIfNonCarryFlag;
            case 0x28:
                return instructionsContainer.jreIfZeroFlag;
            case 0x20:
                return instructionsContainer.jreIfNonZeroFlag;
            case 0xE9:
                return instructionsContainer.jpByHl;
            case 0x10:
                return instructionsContainer.decrementAndJumpOnNonZero;
            case 0xEB:
                return instructionsContainer.exchangeDeHl;
            case 0x08:
                return instructionsContainer.exchangeAfAf;
            case 0xD9:
                return instructionsContainer.exchangeExtraRegisters;
            case 0xE3:
                return instructionsContainer.exchangeHlWithStackTop;
            case 0xDB:
                return instructionsContainer.inputDataToA;
            default: //nothing, going to next switch
        }

        switch (getFirst2Bits(opcode)) {
            case 0b01:
                return decode01bits(opcode);
            case 0b00:
                return decode00bits(opcode);
            case 0b11:
                return decode11bits(opcode);
            default:
                throw new UnsupportedInstructionException(opcode);
        }
    }



    private XBit8 readSecondByte(Memory memory, XBit16 pc) throws MemoryException {
        return memory.read(XBitUtils.incrementBy(pc,1));
    }

    private Instruction decode01bits(XBit8 opcode) {
        if(opcode.getValueOfBits(2,0) == 0b110) {
            return instructionsContainer.loadRegFromMemByHl;
        }

        if(opcode.getValueOfBits(5, 3) == 0b110) {
            return instructionsContainer.loadMemByHlFromReg;
        }

        return instructionsContainer.loadRegFromReg;
    }

    private Instruction decode00bits(XBit8 opcode) throws UnsupportedInstructionException {
        switch (opcode.getValueOfBits(2,0)) {
            case 0b110:
                return instructionsContainer.loadRegFrom8Bit;
            case 0b001:
                return instructionsContainer.loadRegFrom16bit;
            default:
                throw new UnsupportedInstructionException(opcode);
        }
    }

    private Instruction decode11bits(XBit8 opcode) throws UnsupportedInstructionException {
        switch (opcode.getValueOfBits(2,0)) {
            case 0b101:
                return instructionsContainer.pushReg;
            case 0b001:
                return instructionsContainer.popReg;
            case 0b100:
                return instructionsContainer.call16bitIfCondition;
            case 0b000:
                return instructionsContainer.retIfCondition;
            case 0b010:
                return instructionsContainer.jp16bitIfCondition;
            default:
                throw new UnsupportedInstructionException(opcode);
        }
    }

    private Instruction decodeDdOpcode(XBit8 opcode, XBit8 secondByte) throws UnsupportedInstructionException {
        if(secondByte.getUnsignedValue() == 0x21) {
            return instructionsContainer.loadIxFrom16bit;
        }
        if(secondByte.getUnsignedValue() == 0x22) {
            return instructionsContainer.loadMemBy16bitFromIx;
        }
        if(secondByte.getUnsignedValue() == 0x36) {
            return instructionsContainer.loadMemByIxAnd8bitFrom8bit;
        }
        if(secondByte.getUnsignedValue() == 0x2A) {
            return instructionsContainer.loadIxFromMemBy16bit;
        }
        if(secondByte.getUnsignedValue() == 0xF9) {
            return instructionsContainer.loadSpFromIx;
        }
        if(secondByte.getUnsignedValue() == 0xE5) {
            return instructionsContainer.pushIx;
        }
        if(secondByte.getUnsignedValue() == 0xE1) {
            return instructionsContainer.popIx;
        }
        if(secondByte.getUnsignedValue() == 0xE9) {
            return instructionsContainer.jpByIx;
        }
        if(secondByte.getUnsignedValue() == 0xE3) {
            return instructionsContainer.exchangeIxWithStackTop;
        }
        if(secondByte.getValueOfBits(2,0) == 0b110) {
            return instructionsContainer.loadRegFromMemByIxAnd8Bit;
        }
        if(secondByte.getValueOfBits(7,3) == 0b01110) {
            return instructionsContainer.loadMemByIxAnd8BitFromReg;
        }

        throw new UnsupportedInstructionException(opcode);
    }

    private Instruction decodeFdOpcode(XBit8 opcode, XBit8 secondByte) throws UnsupportedInstructionException {

        if(secondByte.getUnsignedValue() == 0x21) {
            return instructionsContainer.loadIyFrom16bit;
        }
        if(secondByte.getUnsignedValue() == 0x22) {
            return instructionsContainer.loadMemBy16bitFromIy;
        }
        if(secondByte.getUnsignedValue() == 0x36) {
            return instructionsContainer.loadMemByIyAnd8bitFrom8bit;
        }
        if(secondByte.getUnsignedValue() == 0x2A) {
            return instructionsContainer.loadIyFromMemBy16bit;
        }
        if(secondByte.getUnsignedValue() == 0xF9) {
            return instructionsContainer.loadSpFromIy;
        }
        if(secondByte.getUnsignedValue() == 0xE5) {
            return instructionsContainer.pushIy;
        }
        if(secondByte.getUnsignedValue() == 0xE1) {
            return instructionsContainer.popIy;
        }
        if(secondByte.getUnsignedValue() == 0xE9) {
            return instructionsContainer.jpByIy;
        }
        if(secondByte.getUnsignedValue() == 0xE3) {
            return instructionsContainer.exchangeIyWithStackTop;
        }
        if(secondByte.getValueOfBits(2,0) == 0b110) {
            return instructionsContainer.loadRegFromMemByIyAnd8bit;
        }
        if(secondByte.getValueOfBits(7,3) == 0b01110) {
            return instructionsContainer.loadMemByIyAnd8BitFromReg;
        }

        throw new UnsupportedInstructionException(opcode);
    }

    private Instruction decodeEdOpcode(XBit8 opcode, XBit8 secondByte) throws UnsupportedInstructionException {
        if(secondByte.getValueOfBits(3,0) == 0b1011) {
            return instructionsContainer.loadRegFromMemBy16Bit;
        }

        if(secondByte.getValueOfBits(3, 0) == 0b0011) {
            return instructionsContainer.loadMemBy16bitFromReg;
        }

        if(secondByte.getValueOfBits(7,6) == 0b01 &&
           secondByte.getValueOfBits(2,0) == 0b000 ) {
            return instructionsContainer.inputDataToR;
        }

        switch (secondByte.getUnsignedValue()) {
            case 0x47:
                return instructionsContainer.loadIFromA;
            case 0x4F:
                return instructionsContainer.loadRFromA;
            case 0x57:
                return instructionsContainer.loadAFromI;
            case 0x5F:
                return instructionsContainer.loadAFromR;
            case 0xA0:
                return instructionsContainer.loadDataAndIncrement;
            case 0xB0:
                return instructionsContainer.loadDataAndIncrementAndRepeat;
            case 0xA8:
                return instructionsContainer.loadDataAndDecrement;
            case 0xB8:
                return instructionsContainer.loadDataAndDecrementAndRepeat;
            case 0xA1:
                return instructionsContainer.compareAndIncrement;
            case 0xB1:
                return instructionsContainer.compareAndIncrementAndRepeat;
            case 0xA9:
                return instructionsContainer.compareAndDecrement;
            case 0xB9:
                return instructionsContainer.compareAndDecrementAndRepeat;
            default:
                throw new UnsupportedInstructionException(opcode, secondByte);
        }
    }

    private int getFirst2Bits(XBit8 opcode) {
        return opcode.getValueOfBits(7,6);
    }


}
