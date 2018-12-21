package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD SP, IX</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>SP ← IX</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>SP, IX</td>
 *     </tr>
 * </table>
 * <br>
 * The 2-byte contents of Index Register IX are loaded to the Stack Pointer (SP)
 *
 */
public class LoadSpFromIx extends Instruction {

    public LoadSpFromIx(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setSp(
                z80.getRegs().getIx()
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(10)
                .executionTime(2.50f)
                .size(2)
                .build();
    }
}
