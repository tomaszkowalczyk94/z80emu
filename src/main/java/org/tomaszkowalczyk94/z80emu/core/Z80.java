package org.tomaszkowalczyk94.z80emu.core;

import lombok.Data;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.decoder.InstructionDecoder;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

@Data
public class Z80 {

    Memory memory = new Memory();
    RegisterBank registerBank = new RegisterBank();
    InstructionDecoder instructionDecoder = new InstructionDecoder();

    int clockCyclesCounter = 0;
    int instructionCounter = 0;


    public void run() throws Z80Exception {
        while (true) {
            runOneInstruction();
        }
    }

    public void runOneInstruction() throws Z80Exception {

        XBit8 opcode = memory.read(registerBank.getRegisterPC());
        Instruction instruction = instructionDecoder.decode(opcode);
        instruction.execute(opcode, this);

        clockCyclesCounter += instruction.getClocks();
        instructionCounter++;

        registerBank.incrementPc(instruction.getSize());
    }

}
