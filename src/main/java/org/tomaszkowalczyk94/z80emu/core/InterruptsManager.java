package org.tomaszkowalczyk94.z80emu.core;


import lombok.Getter;
import lombok.Setter;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.helper.StackHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class InterruptsManager {

    public enum InterruptionMode {
        IM0, IM1, IM2
    }

    @Getter @Setter InterruptionMode interruptionMode = InterruptionMode.IM0;

    @Getter @Setter boolean iff1 = false;
    @Getter @Setter boolean iff2 = false;

    StackHelper stackHelper = new StackHelper();

    private boolean nmiRequest = false;
    private boolean interruptRequest = false;
    XBit8 addressBus;

    public void interruptRequest(XBit8 addressBus) {
        interruptRequest = true;
        this.addressBus = addressBus;
    }

    public void makeNonMaskableInterruptRequest(XBit8 addressBus) {
        nmiRequest = true;
        this.addressBus = addressBus;
    }

    public void handleInterrupts(Z80 z80) throws Z80Exception {
        if(nmiRequest) {
            handleNmiInterrupt(z80);
        }

        if(iff1 && interruptRequest) {
            handleInterrupt(z80);
        }
    }

    private void handleNmiInterrupt(Z80 z80) throws MemoryException {
        iff2 = iff1;

        stackHelper.pushToStack(z80, z80.getRegs().getPc());
        z80.getRegs().setPc(XBit16.valueOfUnsigned(0x0066));
        nmiRequest = false;
    }

    private void handleInterrupt(Z80 z80) throws Z80Exception {
        this.iff1 = this.iff2 = false;
        stackHelper.pushToStack(z80, z80.getRegs().getPc());

        switch (interruptionMode) {
            case IM0:
                Instruction instruction = z80.instructionDecoder.decodeOneByte(addressBus);
                instruction.execute(addressBus, z80);
                break;
            case IM1:
                z80.getRegisterBank().setPc(XBit16.valueOfUnsigned(0x0038));
                break;
            case IM2:
                XBit16 newPcValue = XBit16.valueOfHighAndLow(
                        z80.getRegs().getI(),
                        addressBus
                ).setBit(0, false);

                z80.getRegisterBank().setPc(newPcValue);
                break;
        }

    }
}
