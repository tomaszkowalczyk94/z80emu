package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.ConditionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>CALL cc, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IF cc true: (sp – 1) ← PCH, (sp – 2) ← PCL, pc ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>CALL</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>cc, nn</td>
 *     </tr>
 * </table>
 * <br>
 * If condition cc is true, this instruction pushes the current contents of the Program Counter
 * (PC) onto the top of the external memory stack, then loads the operands nn to PC to point
 * to the address in memory at which the first op code of a subroutine is to be fetched. At the
 * end of the subroutine, a RETurn instruction can be used to return to the original program
 * flow by popping the top of the stack back to PC. If condition cc is false, the Program
 * Counter is incremented as usual, and the program continues with the next sequential
 * instruction. The stack push is accomplished by first decrementing the current contents of
 * the Stack Pointer (SP), loading the high-order byte of the PC contents to the memory
 * address now pointed to by SP; then decrementing SP again, and loading the low-order
 * byte of the PC contents to the top of the stack.
 * Because this process is a 3-byte instruction, the Program Counter was incremented by
 * three before the push is executed.
 * Condition cc is programmed as one of eight statuses that corresponds to condition bits in
 * the Flag Register (Register F). These eight statuses are defined in the following table,
 * which also specifies the corresponding cc bit fields in the assembled object code.
 * 000 Non-Zero (NZ) Z <br />
 * 001 Zero (Z) Z <br />
 * 010 Non Carry (NC) C <br />
 * 011 Carry (C) Z <br />
 * 100 Parity Odd (PO) P/V <br />
 * 101 Parity Even (PE) P/V <br />
 * 110 Sign Positive (P) S <br />
 * 111 Sign Negative (M) S <br />
 */
public class Call16bitIfCondition extends Instruction {

    private ConditionHelper conditionHelper;

    public Call16bitIfCondition(InstructionHelper helper, ConditionHelper conditionHelper) {
        super(helper);
        this.conditionHelper = conditionHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        boolean conditionResult = conditionHelper.checkCondition(
                opcode.getValueOfBits(5, 3),
                z80
        );

        if(conditionResult) {

            XBit16 incrementedPc = XBitUtils.incrementBy(z80.getRegs().getPc(), 3);

            helper.pushToStack(
                    z80,
                    incrementedPc
            );

            //set pc to new call address
            z80.getRegs().setPc(
                    XBit16.valueOfHighAndLow(
                            helper.getThirdByte(z80),
                            helper.getSecondByte(z80)
                    )
            );

            return InstructionResult.builder()
                    .machineCycles(5)
                    .clocks(17)
                    .executionTime(4.25f)
                    .size(3)
                    .autoIncrementPc(false)
                    .build();
        } else {
            return InstructionResult.builder()
                    .machineCycles(3)
                    .clocks(10)
                    .executionTime(2.50f)
                    .size(3)
                    .build();
        }


    }
}
