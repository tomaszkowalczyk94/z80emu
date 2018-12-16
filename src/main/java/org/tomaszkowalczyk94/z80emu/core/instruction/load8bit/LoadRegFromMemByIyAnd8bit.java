package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD r, (IY+d)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>r ← (IY+D)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>r, (lY+d)</td>
 *     </tr>
 * </table>
 * <br>
 * The operand (lY+d) loads the contents of Index Register IY summed with two’s-complement displacement integer, d,
 * to register r, in which r identifies registers A, B, C, D, E, H,
 * or L, assembled as follows in the object code:
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadRegFromMemByIyAnd8bit implements Instruction {
    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 secondByte = getSecondByte(z80);
        XBit8 thirdByte = getThirdByte(z80);

        int regId = secondByte.getValueOfBits(5, 3);

        XBit16 memoryAddress = XBitUtils.incrementBy(z80.getRegs().getIy(), thirdByte.getSignedValue());
        z80.getRegs().set8BitRegisterById(
                (byte)regId,
                z80.getMem().read(memoryAddress)
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(19)
                .executionTime(4.75f)
                .size(3)
                .build();
    }
}
