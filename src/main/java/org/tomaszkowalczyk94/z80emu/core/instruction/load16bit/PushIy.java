package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>PUSH IY</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(SP – 2) ← IYL, (SP – 1) ← IYH</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>PUSH</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>IY</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of Index Register IY are pushed to the external memory last-in, first-out
 * (LIFO) stack. The Stack Pointer (SP) Register pair holds the 16-bit address of the current
 * top of the Stack. This instruction first decrements the SP and loads the high-order byte of
 * IY to the memory address specified by SP; then decrements SP again and loads the low
 * order byte to the memory location corresponding to this new address in SP.
 *
 */
public class PushIy extends Instruction {

    public PushIy(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        helper.pushToStack(z80, z80.getRegs().getIy());

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(15)
                .executionTime(3.75f)
                .size(2)
                .build();
    }
}
