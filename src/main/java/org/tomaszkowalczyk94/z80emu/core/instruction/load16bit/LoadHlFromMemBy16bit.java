package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD HL, (nn)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>H ← (nn + 1), L ← (nn)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>HL, (nn)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of memory address (nn) are loaded to the low-order portion of register pair
 * HL (Register L), and the contents of the next highest memory address (nn+ 1) are loaded
 * to the high-order portion of HL (Register H). The first n operand after the op code is the
 * low-order byte of nn.
 *
 */
public class LoadHlFromMemBy16bit implements Instruction {

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 addressOfL = XBit16.valueOfHighAndLow(getThirdByte(z80), getSecondByte(z80));
        XBit16 addressOfH = XBitUtils.incrementBy(addressOfL, 1);

        z80.getRegs().setL(
                z80.getMem().read(addressOfL)
        );

        z80.getRegs().setH(
                z80.getMem().read(addressOfH)
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(16)
                .executionTime(4.0f)
                .size(3)
                .build();
    }
}
