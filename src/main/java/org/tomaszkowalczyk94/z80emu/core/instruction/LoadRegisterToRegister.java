package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

/**
 * <h2>LD r, r'</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>r, ← r′ </td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>r, r</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of any register r' are loaded to any other register r. r, r' identifies any of the
 * registers A, B, C, D, E, H, or L, assembled as follows in the object code:
 */
public class LoadRegisterToRegister implements Instruction {

    @Override
    public void execute(XBit8 instruction, Z80 cpu) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getMachineCycles() {
        return 1;
    }

    @Override
    public int getClocks() {
        return 4;
    }

    @Override
    public float getExecutionTime() {
        return 1.0f;
    }
}
