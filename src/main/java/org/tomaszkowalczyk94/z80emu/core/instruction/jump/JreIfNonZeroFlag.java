package org.tomaszkowalczyk94.z80emu.core.instruction.jump;


import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.ConditionHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.JumpHelper;

/**
 * <h2>JR NZ, e</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>
 *              If Z = 1, continue <br />
 *              If Z = 0, PC ← pc + e
 *         </td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JR</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>NZ, e</td>
 *     </tr>
 * </table>
 * <br>
 * This instruction provides for conditional branching to other segments of a program
 * depending on the results of a test on the Zero Flag. If the flag = 0, the value of displacement e is added to the Program Counter (PC) and the next instruction is fetched from the
 * location designated by the new contents of the PC. The jump is measured from the address
 * of the instruction op code and contains a range of –126 to +129 bytes. The assembler automatically adjusts for the twice incremented PC.
 * If the Zero Flag = 1, the next instruction executed is taken from the location following this
 * instruction.
 */
public class JreIfNonZeroFlag extends Instruction {
    private JumpHelper jumpHelper;

    public JreIfNonZeroFlag(InstructionHelper helper, JumpHelper jumpHelper) {
        super(helper);
        this.jumpHelper = jumpHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        return jumpHelper.executeRelativeJumpCondition(z80, ConditionHelper.Condition.NON_ZERO);
    }
}
