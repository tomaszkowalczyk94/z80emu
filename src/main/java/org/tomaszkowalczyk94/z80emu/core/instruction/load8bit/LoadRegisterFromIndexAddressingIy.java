package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

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
public class LoadRegisterFromIndexAddressingIy implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 secondByte = z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 1)
        );
        XBit8 thirdByte = z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 2)
        );

        int regId = secondByte.getValueOfBits(5, 3);

        XBit16 memoryAddress = XBitUtils.incrementBy(z80.getRegs().getIy(), thirdByte.getSignedValue());
        z80.getRegs().set8BitRegisterById(
                (byte)regId,
                z80.getMem().read(memoryAddress)
        );
    }

    @Override
    public int getMachineCycles() {
        return 5;
    }

    @Override
    public int getClocks() {
        return 19;
    }

    @Override
    public float getExecutionTime() {
        return 4.75f;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
