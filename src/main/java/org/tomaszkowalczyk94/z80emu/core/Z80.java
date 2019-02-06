package org.tomaszkowalczyk94.z80emu.core;

import lombok.Getter;
import lombok.Setter;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.decoder.InstructionDecoder;
import org.tomaszkowalczyk94.z80emu.core.io.IoDevice;
import org.tomaszkowalczyk94.z80emu.core.io.SimpleIoDevice;
import org.tomaszkowalczyk94.z80emu.core.memory.Memory;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.PC;

public class Z80 {

    @Getter @Setter Memory memory = new Memory();
    @Getter @Setter IoDevice io = new SimpleIoDevice(8);

    @Getter RegisterBank registerBank = new RegisterBank();
    @Getter InterruptsManager interruptsManager = new InterruptsManager();
    private InstructionDecoder instructionDecoder = new InstructionDecoder();


    @Getter int clockCyclesCounter = 0;
    @Getter int instructionCounter = 0;

    public void runOneInstruction() throws Z80Exception {
        interruptsManager.handleInterrupts(this);

        Instruction instruction = instructionDecoder.decode(memory, registerBank.getPc());

        InstructionResult result = instruction.execute(
                memory.read(registerBank.getPc()),
                this
        );

        clockCyclesCounter += result.getClocks();
        instructionCounter++;

        if(result.isAutoIncrementPc()) {
            registerBank.incrementReg16bit(PC, result.getSize());
        }

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

    public void makeInterrupt(XBit8 addressBus) {

    }

    public void makeNonMaskableInterrupt(XBit8 addressBus) {

    }

    public void reset(XBit8 addressBus) {

    }
}
