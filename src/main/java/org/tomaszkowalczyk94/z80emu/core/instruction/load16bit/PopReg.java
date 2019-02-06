package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.helper.StackHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.AF;

/**
 * <h2>POP qq</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>qqH ← (SP+1), qqL ← (SP)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>POP</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>qq</td>
 *     </tr>
 * </table>
 * <br>
 * The top two bytes of the external memory last-in, first-out (LIFO) stack are popped to reg
 * ister pair qq. The Stack Pointer (SP) Register pair holds the 16-bit address of the current
 * top of the Stack. This instruction first loads to the low-order portion of qq, the byte at the
 * memory location corresponding to the contents of SP; then SP is incremented and the con
 * tents of the corresponding adjacent memory location are loaded to the high-order portion
 * of qq and the SP is now incremented again. The operand qq identifies register pair BC,
 * DE, HL, or AF, assembled as follows in the object code:
 * BC 00<br/>
 * DE 01<br/>
 * HL 10<br/>
 * AF 11<br/>
 */
public class PopReg extends Instruction {

    StackHelper stackHelper;

    public PopReg(InstructionHelper helper, StackHelper stackHelper) {
        super(helper);
        this.stackHelper = stackHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        int regId = opcode.getValueOfBits(5, 4);
        XBit16 valueFromStack = stackHelper.popFromStack(z80);

        z80.getRegs().set16BitRegisterById(
                (byte)regId,
                valueFromStack,
                AF
        );

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.50f)
                .size(1)
                .build();
    }
}
