package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.helper.FlagHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

public class Dec8bitReg extends Instruction {

    FlagHelper flagHelper;

    public Dec8bitReg(InstructionHelper helper, FlagHelper flagHelper) {
        super(helper);
        this.flagHelper = flagHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        byte regId = (byte) opcode.getValueOfBits(5, 3);

        XBit8 value = z80.getRegs().get8BitRegisterById(regId);

        XBit8 newValue = XBitUtils.incrementBy(value, -1);

        z80.getRegs().set8BitRegisterById(regId, newValue);

        flagHelper.setSignFlagBy8bit(z80, newValue);
        flagHelper.setZeroFlabBy8bit(z80, newValue);
        //@todo PV, H flag
        z80.getRegs().setFlag(FlagRegManager.Flag.N, false);

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(4)
                .executionTime(1.00f)
                .size(1)
                .build();
    }
}
