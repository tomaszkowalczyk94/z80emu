package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.SP;

/**
 * <h2>LD dd, (nn)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>ddh ← (nn + 1) ddl ← (nn)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>dd, (nn)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of address (nn) are loaded to the low-order portion of register pair dd, and the
 * contents of the next highest memory address (nn+ 1) are loaded to the high-order portion
 * of dd. Register pair dd defines BC, DE, HL, or SP register pairs, assembled as follows in
 * the object code:<br/>
 * BC 00<br/>
 * DE 01<br/>
 * HL 10<br/>
 * SP 11<br/>
 *
 */
public class LoadRegFromMemBy16bit extends Instruction {

    public LoadRegFromMemBy16bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit16 addressOfL = XBit16.valueOfHighAndLow(helper.getFourthByte(z80), helper.getThirdByte(z80));
        XBit16 addressOfH = XBitUtils.incrementBy(addressOfL, 1);

        XBit16 newValue = XBit16.valueOfHighAndLow(
                z80.getMem().read(addressOfH),
                z80.getMem().read(addressOfL)
        );

        z80.getRegs().set16BitRegisterById(
                (byte)helper.getSecondByte(z80).getValueOfBits(5,4),
                newValue,
                SP
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(20)
                .executionTime(5.0f)
                .size(4)
                .build();
    }
}
