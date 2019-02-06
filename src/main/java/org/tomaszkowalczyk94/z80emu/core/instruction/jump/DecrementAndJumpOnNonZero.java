package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.JumpHelper;

/**
 * <h2>DJNZ, e</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>
 *              B ← B – 1
 *               If B = 0, continue
 *               If B ≠ 0, PC ← PC + e
 *         </td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>DJNZ </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>e</td>
 *     </tr>
 * </table>
 * <br>
 * This instruction is similar to the conditional jump instructions except that a register value
 * is used to determine branching. Register B is decremented, and if a nonzero value remains,
 * the value of displacement e is added to the Program Counter (PC). The next instruction is
 * fetched from the location designated by the new contents of the PC. The jump is measured
 * from the address of the instruction op code and contains a range of –126 to +129 bytes.
 * The assembler automatically adjusts for the twice incremented PC.
 */
public class DecrementAndJumpOnNonZero extends Instruction{
    private JumpHelper jumpHelper;

    public DecrementAndJumpOnNonZero(InstructionHelper helper, JumpHelper jumpHelper) {
        super(helper);
        this.jumpHelper = jumpHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        InstructionResult.InstructionResultBuilder builder = InstructionResult.builder();
        builder.size(2);

        z80.getRegs().setB(
                XBitUtils.incrementBy(z80.getRegs().getB(), -1)
        );

        if(z80.getRegs().getB().getUnsignedValue() != 0) {

            jumpHelper.jumpRelative(z80, helper.getSecondByte(z80).getSignedValue());

            return builder
                    .machineCycles(3)
                    .clocks(13)
                    .executionTime(3.25f)
                    .build();
        } else {
            return builder
                    .machineCycles(2)
                    .clocks(8)
                    .executionTime(2.00f)
                    .build();
        }
    }
}
