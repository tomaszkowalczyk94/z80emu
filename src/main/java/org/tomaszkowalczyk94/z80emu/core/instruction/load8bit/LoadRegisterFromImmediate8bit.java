package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD r,n</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>r ← n</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>r, n</td>
 *     </tr>
 * </table>
 * <br>
 * The 8-bit integer n is loaded to any register r, in which r identifies registers A, B, C, D, E,
 * H, or L, assembled as follows in the object code:
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadRegisterFromImmediate8bit implements Instruction {

    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 immediate8bit = z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 1)
        );

        byte registerId = (byte)opcode.getValueOfBits(5, 3);

        z80.getRegs().set8BitRegisterById(registerId, immediate8bit);
    }

    @Override
    public int getMachineCycles() {
        return 2;
    }

    @Override
    public int getClocks() {
        return 7;
    }

    @Override
    public float getExecutionTime() {
        return 1.75f;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
