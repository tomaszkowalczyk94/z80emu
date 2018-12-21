package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>RET cc</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>If cc true: PCL ← (sp), pCH ← (sp+1)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>RET</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>cc</td>
 *     </tr>
 * </table>
 * <br>
 * If condition cc is true, the byte at the memory location specified by the contents of the
 * Stack Pointer (SP) Register pair is moved to the low-order eight bits of the Program Counter (PC). The SP is incremented and the byte at the memory location specified by the new
 * contents of the SP are moved to the high-order eight bits of the PC. The SP is incremented
 * again. The next op code following this instruction is fetched from the memory location
 * specified by the PC. This instruction is normally used to return to the main line program at
 * the completion of a routine entered by a CALL instruction. If condition cc is false, the PC
 * is simply incremented as usual, and the program continues with the next sequential
 * instruction. Condition cc is programmed as one of eight status that correspond to condition
 * bits in the Flag Register (Register F). These eight status are defined in the following table,
 * which also specifies the corresponding cc bit fields in the assembled object code.
 * 000 Non-Zero (NZ) Z <br />
 * 001 Zero (Z) Z <br />
 * 010 Non Carry (NC) C <br />
 * 011 Carry (C) Z <br />
 * 100 Parity Odd (PO) P/V <br />
 * 101 Parity Even (PE) P/V <br />
 * 110 Sign Positive (P) S <br />
 * Sign Negative (M) S <br />
 */
public class RetIfCondition extends Instruction {
    public RetIfCondition(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        boolean conditionResult = helper.getConditionHelper().checkCondition(
                opcode.getValueOfBits(5, 3),
                z80
        );

        if(conditionResult) {
            z80.getRegs().setPc(
                    helper.read16bitFromMemory(z80, z80.getRegs().getSp())
            );

            z80.getRegs().setSp(
                    XBitUtils.incrementBy(z80.getRegs().getSp(), 2)
            );

            return InstructionResult.builder()
                    .machineCycles(3)
                    .clocks(11)
                    .executionTime(2.75f)
                    .size(1)
                    .autoIncrementPc(false)
                    .build();

        } else {
            return InstructionResult.builder()
                    .machineCycles(1)
                    .clocks(5)
                    .executionTime(1.25f)
                    .size(1)
                    .build();
        }

    }
}
