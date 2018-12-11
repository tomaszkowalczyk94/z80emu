package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD A, (BC)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A ← (BC)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>A, (BC)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the memory location specified by the contents of the BC register pair are
 * loaded to the Accumulator.
 */
public class LoadAFromMemByBc implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit8 valueFromMemory = z80.getMem().read(
                z80.getRegs().getBC()
        );

        z80.getRegs().setA(
                valueFromMemory
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
