package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.JumpHelper;

/**
 * <h2>JR e</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>PC ← PC + e</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JR</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>e</td>
 *     </tr>
 * </table>
 * <br>
 * This instruction provides for unconditional branching to other segments of a program. The
 * value of displacement e is added to the Program Counter (PC) and the next instruction is
 * fetched from the location designated by the new contents of the PC. This jump is mea
 * sured from the address of the instruction op code and contains a range of –126 to +129
 * bytes. The assembler automatically adjusts for the twice incremented PC.
 */
public class Jre extends Instruction {

    private JumpHelper jumpHelper;

    public Jre(InstructionHelper helper, JumpHelper jumpHelper) {
        super(helper);
        this.jumpHelper = jumpHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        jumpHelper.jumpRelative(z80, helper.getSecondByte(z80).getSignedValue());

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(12)
                .executionTime(3.0f)
                .size(2)
                .build();
    }
}
