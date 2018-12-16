package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD A, (nn)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A ← (nn)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>A, (nn)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the memory location specified by the operands nn are loaded to the Accumulator.
 * The first n operand after the op code is the low-order byte of a 2-byte memory
 * address.
 */
public class LoadAFromMemBy16bit implements Instruction {
    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit16 address = XBit16.valueOfHighAndLow(
                getThirdByte(z80),
                getSecondByte(z80)
        );

        z80.getRegs().setA(
               z80.getMem().read(address)
        );

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(13)
                .executionTime(3.25f)
                .size(3)
                .build();
    }
}
