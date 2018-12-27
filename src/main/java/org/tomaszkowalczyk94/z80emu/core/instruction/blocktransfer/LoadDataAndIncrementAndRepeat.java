package org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>LDIR</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>repeat {(DE) ← (HL), DE ← DE + 1, HL ← HL + 1, BC ← BC – 1} while (BC ≠ 0)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LDIR </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>None</td>
 *     </tr>
 * </table>
 * <br>
 * This 2-byte instruction transfers a byte of data from the memory location addressed by the
 * contents of the HL register pair to the memory location addressed by the DE register pair.
 * Both these register pairs are incremented and the Byte Counter (BC) Register pair is dec
 * remented. If decrementing allows the BC to go to 0, the instruction is terminated. If BC is
 * not 0, the program counter is decremented by two and the instruction is repeated. Inter
 * rupts are recognized and two refresh cycles are executed after each data transfer. When the
 * BC is set to 0 prior to instruction execution, the instruction loops through 64 KB.
 * Condition Bits Affected<br />
 * S is not affected.<br />
 * Z is not affected.<br />
 * H is reset.<br />
 * P/V is set if BC – 1 ≠ 0; otherwise, it is reset.<br />
 * N is reset.<br />
 * C is not affected.<br />
 *
 */
public class LoadDataAndIncrementAndRepeat extends Instruction {
    public LoadDataAndIncrementAndRepeat(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        return null;
    }
}
