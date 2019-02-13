package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.helper.FlagHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

public class Inc16bitReg extends Instruction {

    FlagHelper flagHelper;

    public Inc16bitReg(InstructionHelper helper, FlagHelper flagHelper) {
        super(helper);
        this.flagHelper = flagHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        byte regId = (byte) opcode.getValueOfBits(5, 4);

        XBit16 value = z80.getRegs().get16BitRegisterById(regId, RegisterBank.Reg16bit.SP);

        XBit16 newValue = XBitUtils.increment(value);

        z80.getRegs().set16BitRegisterById(regId, newValue, RegisterBank.Reg16bit.SP);

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(6)
                .executionTime(1.50f)
                .size(1)
                .build();
    }
}
