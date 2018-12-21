package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD SP, HL</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>SP ← HL</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>SP, HL</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the register pair HL are loaded to the Stack Pointer (SP).
 *
 */
public class LoadSpFromHl extends Instruction {

    public LoadSpFromHl(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setSp(
                z80.getRegs().getHL()
        );

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(6)
                .executionTime(1.5f)
                .size(1)
                .build();
    }
}
