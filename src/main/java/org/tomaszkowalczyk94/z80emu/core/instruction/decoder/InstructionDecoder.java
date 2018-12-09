package org.tomaszkowalczyk94.z80emu.core.instruction.decoder;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.decoder.exception.UnsupportedInstructionException;

public class InstructionDecoder {

    private final InstructionsContainer instructionsContainer = new InstructionsContainer();

    public Instruction decode(XBit8 opcode) throws UnsupportedInstructionException {
        short opcodeUnsignedValue = opcode.getUnsignedValue();

        switch (opcodeUnsignedValue) {
            case 0xDD:
                return instructionsContainer.loadRegisterFromIndexAddressingIx;
            case 0xFD:
                return instructionsContainer.loadRegisterFromIndexAddressingIy;
            default: //nothing, going to next switch
        }

        switch (getFirst2Bits(opcode)) {
            case 0b01:
                return decode01bits(opcode);
            case 0b00:
                return instructionsContainer.loadRegisterFromImmediate8bit;
            default:
                throw new UnsupportedInstructionException(opcode);
        }
    }

    private Instruction decode01bits(XBit8 opcode) {
        if(opcode.getValueOfBits(2,0) == 0b110) {
            return instructionsContainer.loadRegisterFromMemoryAddressingByHl;
        }

        if(opcode.getValueOfBits(5, 3) == 0b110) {
            return instructionsContainer.loadMemoryAddressingByHlFromRegister;
        }

        return instructionsContainer.loadRegisterFromRegister;
    }

    private int getFirst2Bits(XBit8 opcode) {
        return opcode.getValueOfBits(7,6);
    }
}
