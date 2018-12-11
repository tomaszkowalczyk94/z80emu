package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD (HL), n</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(HL) ← n</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(HL), n</td>
 *     </tr>
 * </table>
 * <br>
 * The n integer is loaded to the memory address specified by the contents of the HL register
 * pair.
 */
public class LoadMemoryAddressingByHlFromImmediate8bit implements Instruction{
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 immediateValue = getSecondByte(z80);

        z80.getMem().write(
                z80.getRegs().getHL(),
                immediateValue
        );
    }

    @Override
    public int getMachineCycles() {
        return 3;
    }

    @Override
    public int getClocks() {
        return 10;
    }

    @Override
    public float getExecutionTime() {
        return 2/5f;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
