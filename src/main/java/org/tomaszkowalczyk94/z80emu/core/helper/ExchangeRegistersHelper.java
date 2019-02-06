package org.tomaszkowalczyk94.z80emu.core.helper;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

public class ExchangeRegistersHelper {

    private InstructionHelper helper;

    public ExchangeRegistersHelper(InstructionHelper instructionHelper) {
        this.helper = instructionHelper;
    }

    public void exchange16bitRegWithStackTop(Z80 z80, RegisterBank.Reg16bit regEnum) throws Z80Exception{

        XBit16 regValue = z80.getRegs().get16bitRegister(regEnum);
        XBit16 topStackValue = helper.read16bitFromMemory(z80, z80.getRegs().getSp());

        z80.getRegs().set16bitRegister(regEnum, topStackValue);
        helper.write16bitToMemory(
                z80,
                z80.getRegs().getSp(),
                regValue
        );

    }
}
