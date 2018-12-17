package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import lombok.RequiredArgsConstructor;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (nn), IX</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn + 1) ← IXh, (nn) ← IXI</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), IX</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte in Index Register IX is loaded to memory address (nn); the upper order
 * byte is loaded to the next highest address (nn+ 1). The first n operand after the op code is
 * the low-order byte of nn.
 *
 */
@RequiredArgsConstructor
public class LoadMemBy16bitFromIx implements Instruction {
    final InstructionHelper instructionHelper;

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                getFourthByte(z80),
                getThirdByte(z80)
        );

        instructionHelper.write16bitToMemory(
                z80,
                address,
                z80.getRegs().getIx()
        );

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(20)
                .executionTime(5.00f)
                .size(4)
                .build();

    }
}
