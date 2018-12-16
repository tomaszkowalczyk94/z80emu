package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD IX, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IX ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>IX, nn</td>
 *     </tr>
 * </table>
 * <br>
 * The n integer is loaded to Index Register IX. The first n operand after the op code is the
 * low-order byte
 *
 */
public class LoadIxFrom16bit implements Instruction {
    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getRegs().setIx(
                XBit16.valueOfHighAndLow(getFourthByte(z80), getThirdByte(z80))
        );

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(14)
                .executionTime(3.50f)
                .size(4)
                .build();
    }
}
