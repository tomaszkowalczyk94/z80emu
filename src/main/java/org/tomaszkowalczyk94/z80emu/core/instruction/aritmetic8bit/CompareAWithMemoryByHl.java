package org.tomaszkowalczyk94.z80emu.core.instruction.aritmetic8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.helper.FlagHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.C;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.N;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;

public class CompareAWithMemoryByHl extends Instruction {

    FlagHelper flagHelper;

    public CompareAWithMemoryByHl(InstructionHelper helper, FlagHelper flagHelper) {
        super(helper);

        this.flagHelper = flagHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 hl = z80.getRegs().getHL();

        XBit8 memValue = z80.getMem().read(hl);

        XBitUtils.Arithmetic8bitResult result = XBitUtils.subTwo8bits(z80.getRegs().getA(), memValue);

        flagHelper.setHalfCarryFlagSub(z80, z80.getRegs().getA(), memValue);

        flagHelper.setSignFlagBy8bit(z80, result.result);
        flagHelper.setZeroFlabBy8bit(z80, result.result);
        z80.getRegs().setFlag(
                PV,
                (z80.getRegs().getBC().getSignedValue() != 0)
        );
        z80.getRegs().setFlag(N, true);


        z80.getRegs().setFlag(C,
                ((z80.getRegs().getA().getSignedValue()-memValue.getSignedValue()) & 0x100) != 0
                );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(7)
                .executionTime(1.75f)
                .size(1)
                .build();
    }
}
