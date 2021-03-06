package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.SP;

/**
 * <h2>LD (nn), dd</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn + 1) ← ddh, (nn) ← ddl</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), dd</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte of register pair dd is loaded to memory address (nn); the upper byte is
 * loaded to memory address (nn+ 1). Register pair dd defines either BC, DE, HL, or SP,
 * assembled as follows in the object code:<br/>
 * BC 00<br/>
 * DE 01<br/>
 * HL 10<br/>
 * SP 11<br/>
 */
public class LoadMemBy16bitFromReg extends Instruction {

    public LoadMemBy16bitFromReg(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                helper.getFourthByte(z80),
                helper.getThirdByte(z80)
        );

        byte regId = (byte)helper.getSecondByte(z80).getValueOfBits(5,4);

        helper.write16bitToMemory(
                z80,
                address,
                z80.getRegs().get16BitRegisterById(regId, SP)
        );

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(20)
                .executionTime(5.00f)
                .size(4)
                .build();
    }
}
