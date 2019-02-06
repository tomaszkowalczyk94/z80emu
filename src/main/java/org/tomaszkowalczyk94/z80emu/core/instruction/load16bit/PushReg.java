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
 * <h2>PUSH qq</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(SP – 2) ← qqL, (SP – 1) ← qqH</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>PUSH</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>qq</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the register pair qq are pushed to the external memory last-in, first-out
 * (LIFO) stack. The Stack Pointer (SP) Register pair holds the 16-bit address of the current
 * top of the Stack. This instruction first decrements SP and loads the high-order byte of reg
 * ister pair qq to the memory address specified by the SP. The SP is decremented again and
 * loads the low-order byte of qq to the memory location corresponding to this new address
 * in the SP. The operand qq identifies register pair BC, DE, HL, or AF, assembled as follows
 * in the object code: <br/>
 * BC 00<br/>
 * DE 01<br/>
 * HL 10<br/>
 * <b>AF</b> 11<br/>
 *
 */
public class PushReg extends Instruction {
    StackHelper stackHelper;

    public PushReg(InstructionHelper helper, StackHelper stackHelper) {
        super(helper);
        this.stackHelper = stackHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 valueToPush = z80.getRegs().get16BitRegisterById(
                (byte) opcode.getValueOfBits(5, 4),
                AF
        );

        stackHelper.pushToStack(z80, valueToPush);

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(11)
                .executionTime(2.75f)
                .size(1)
                .build();
    }
}
