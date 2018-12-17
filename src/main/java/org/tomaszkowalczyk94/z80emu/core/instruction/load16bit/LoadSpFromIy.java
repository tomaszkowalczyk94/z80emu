package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD SP, IY</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>SP ← IY</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>SP, IY</td>
 *     </tr>
 * </table>
 * <br>
 * The 2-byte contents of Index Register IY are loaded to the Stack Pointer SP
 *
 */
public class LoadSpFromIy extends Instruction {

    public LoadSpFromIy(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getRegs().setSp(
                z80.getRegs().getIy()
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(10)
                .executionTime(2.50f)
                .size(2)
                .build();
    }
}
