package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD (DE), A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(DE) ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(DE), A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the memory location specified by
 * the contents of the DE register pair.
 */
public class LoadMemoryByDeFromA implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getMem().write(
                z80.getRegs().getDE(),
                z80.getRegs().getA()
        );
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
        return 1;
    }
}
