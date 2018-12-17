package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

public class LoadIyFromMemBy16bit implements Instruction {

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 addressOfL = XBit16.valueOfHighAndLow(getFourthByte(z80), getThirdByte(z80));
        XBit16 addressOfH = XBitUtils.incrementBy(addressOfL, 1);

        XBit16 newValue = XBit16.valueOfHighAndLow(
                z80.getMem().read(addressOfH),
                z80.getMem().read(addressOfL)
        );

        z80.getRegs().setIy(
                newValue
        );

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(20)
                .executionTime(5.0f)
                .size(4)
                .build();
    }
}