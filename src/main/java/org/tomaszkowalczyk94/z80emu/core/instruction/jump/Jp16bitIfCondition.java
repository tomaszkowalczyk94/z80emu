package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>JP cc, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IF cc true, PC ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JP</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>cc, nn</td>
 *     </tr>
 * </table>
 * <br>
 * If condition cc is true, the instruction loads operand nn to register pair Program Counter
 * (PC), and the program continues with the instruction beginning at address nn. If condition
 * cc is false, the Program Counter is incremented as usual, and the program continues with
 * the next sequential instruction. Condition cc is programmed as one of eight statuses that
 * correspond to condition bits in the Flag Register (Register F). These eight statuses are
 * defined in the following table, which specifies the corresponding cc bit fields in the
 * assembled object code.
 * 000 Non-Zero (NZ) Z <br />
 * 001 Zero (Z) Z <br />
 * 010 Non Carry (NC) C <br />
 * 011 Carry (C) Z <br />
 * 100 Parity Odd (PO) P/V <br />
 * 101 Parity Even (PE) P/V <br />
 * 110 Sign Positive (P) S <br />
 * 111 Sign Negative (M) S <br />
 */
public class Jp16bitIfCondition extends Instruction {
    public Jp16bitIfCondition(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        InstructionResult.InstructionResultBuilder builder = InstructionResult.builder();

        boolean conditionResult = helper.getConditionHelper().checkCondition(
                opcode.getValueOfBits(5, 3),
                z80
        );

        if(conditionResult) {

            z80.getRegs().setPc(
                    XBit16.valueOfHighAndLow(
                            helper.getThirdByte(z80),
                            helper.getSecondByte(z80)
                    )
            );

            builder.autoIncrementPc(false);
        }

        return builder
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.50f)
                .size(3)
                .build();
    }
}
