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

    public Instruction decode(Memory memory, XBit16 pc) throws UnsupportedInstructionException, MemoryException {
        XBit8 opcode = memory.read(pc);

        short opcodeUnsignedValue = opcode.getUnsignedValue();
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
            default: //nothing, going to next switch
        }

        switch (getFirst2Bits(opcode)) {
            case 0b01:
                return decode01bits(opcode);
            case 0b00:
                return decode00bits(opcode);
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

    private Instruction decodeDdOpcode(XBit8 opcode, XBit8 secondByte) throws UnsupportedInstructionException {
        if(secondByte.getUnsignedValue() == 0x21) {
            return instructionsContainer.loadIxFrom16bit;
        }
        if(secondByte.getUnsignedValue() == 0x36) {
            return instructionsContainer.loadMemByIxAnd8bitFrom8bit;
        }
        if(secondByte.getUnsignedValue() == 0x2A) {
            return instructionsContainer.loadIxFromMemBy16bit;
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
        if(secondByte.getUnsignedValue() == 0x36) {
            return instructionsContainer.loadMemByIyAnd8bitFrom8bit;
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

        switch (secondByte.getUnsignedValue()) {
            case 0x47:
                return instructionsContainer.loadIFromA;
            case 0x4F:
                return instructionsContainer.loadRFromA;
            case 0x57:
                return instructionsContainer.loadAFromI;
            case 0x5F:
                return instructionsContainer.loadAFromR;
            default:
                throw new UnsupportedInstructionException(opcode, secondByte);
        }
    }

    private int getFirst2Bits(XBit8 opcode) {
        return opcode.getValueOfBits(7,6);
    }


}
