package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD (nn), A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn) ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the memory address specified by the oper
 * and nn. The first n operand after the op code is the low-order byte of nn.
 */
public class LoadMemoryBy16BitFromA implements Instruction{
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                getThirdByte(z80),
                getSecondByte(z80)
        );

        z80.getMem().write(
                address,
                z80.getRegs().getA()
        );
    }

    @Override
    public int getMachineCycles() {
        return 4;
    }

    @Override
    public int getClocks() {
        return 13;
    }

    @Override
    public float getExecutionTime() {
        return 3.25f;
    }

    @Override
    public int getSize() {
        return 3;
    }
}
