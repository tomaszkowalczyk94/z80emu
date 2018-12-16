package org.tomaszkowalczyk94.z80emu.core;

import lombok.Data;
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
    boolean iff1 = false;
    boolean iff2 = false;

    public void run() throws Z80Exception {
        while (true) {
            runOneInstruction();
        }
    }

    public void runOneInstruction() throws Z80Exception {

        Instruction instruction = instructionDecoder.decode(memory, registerBank.getPc());

        instruction.execute(
                memory.read(registerBank.getPc()),
                this
        );

        clockCyclesCounter += instruction.getClocks();
        instructionCounter++;

        registerBank.incrementPc(instruction.getSize());
    }

    /**
     * alias for {@link Z80#getMemory()}
     */
    public Memory getMem() {
        return getMemory();
    }

    /**
     * alias for {@link Z80#getRegisterBank()}
     */
    public RegisterBank getRegs() {
        return getRegisterBank();
    }
}
