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
 * <h2>LD dd, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>dd ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>dd, nn</td>
 *     </tr>
 * </table>
 * <br>
 * The 2-byte integer nn is loaded to the dd register pair, in which dd defines the BC, DE,
 * HL, or SP register pairs, assembled as follows in the object code: <br/>
 * BC 00<br/>
 * DE 01<br/>
 * HL 10<br/>
 * SP 11<br/>
 *
 */
public class LoadRegFrom16bit extends Instruction {

    public LoadRegFrom16bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 newValue = XBit16.valueOfHighAndLow(
                helper.getThirdByte(z80),
                helper.getSecondByte(z80)
        );

        z80.getRegs().set16BitRegisterById(
                (byte)opcode.getValueOfBits(5,4),
                newValue,
                SP
        );


        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(10)
                .executionTime(2.50f)
                .size(3)
                .build();
    }
}
