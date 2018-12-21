package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>POP IX</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IXH ← (SP+1), IXL ← (SP)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>POP</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>IX</td>
 *     </tr>
 * </table>
 * <br>
 * The top two bytes of the external memory last-in, first-out (LIFO) stack are popped to
 * Index Register IX. The Stack Pointer (SP) Register pair holds the 16-bit address of the
 * current top of the Stack. This instruction first loads to the low-order portion of IX the byte
 * at the memory location corresponding to the contents of SP; then SP is incremented and
 * the contents of the corresponding adjacent memory location are loaded to the high-order
 * portion of IX. The SP is incremented again.
 */
public class PopIx extends Instruction {
    public PopIx(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setIx(
                helper.popFromStack(z80)
        );

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(14)
                .executionTime(4.50f)
                .size(2)
                .build();
    }
}
