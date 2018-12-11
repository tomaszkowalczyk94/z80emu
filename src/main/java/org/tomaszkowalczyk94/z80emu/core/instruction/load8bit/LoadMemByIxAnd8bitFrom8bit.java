package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD (IX+d), n</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(IX+d) ← n</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(IX+d), n</td>
 *     </tr>
 * </table>
 * <br>
 * The n operand is loaded to the memory address specified by the sum of Index Register IX
 * and the two’s complement displacement operand d.
 */
public class LoadMemByIxAnd8bitFrom8bit implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 offset = getThirdByte(z80);
        XBit8 immediateValue = getFourthByte(z80);

        XBit16 memAddress = XBitUtils.incrementBy(
                z80.getRegs().getIx(),
                offset.getSignedValue()
        );

        z80.getMem().write(memAddress, immediateValue);
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
        return 4;
    }
}
