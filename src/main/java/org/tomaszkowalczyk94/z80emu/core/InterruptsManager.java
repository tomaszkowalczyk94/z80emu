package org.tomaszkowalczyk94.z80emu.core;


import lombok.Getter;
import lombok.Setter;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.helper.StackHelper;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

public class InterruptsManager {

    enum InterruptionMode {
        IM0, IM1, IM2
    }

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

    public void handleInterrupts(Z80 z80) throws MemoryException {
        if(nmiRequest) {
            handleNmiInterrupt(z80);
        }

        if(z80.getInterruptsManager().isIff1() && interruptRequest) {
            handleInterupt(z80);
        }
    }

    protected void handleNmiInterrupt(Z80 z80) throws MemoryException {
        z80.getInterruptsManager().setIff2(
                z80.getInterruptsManager().isIff1()
        );

        stackHelper.pushToStack(z80, z80.getRegs().getPc());
        z80.getRegs().setPc(XBit16.valueOfUnsigned(0x0066));
        nmiRequest = false;
    }

    protected void handleInterupt(Z80 z80) {

    }
}
