package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD IY, nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IY ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>IY, nn</td>
 *     </tr>
 * </table>
 * <br>
 * The nn integer is loaded to Index Register IY. The first n operand after the op code is the
 * low-order byte.
 *
 */
public class LoadIyFrom16bit extends Instruction {

    public LoadIyFrom16bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getRegs().setIy(
                XBit16.valueOfHighAndLow(helper.getFourthByte(z80), helper.getThirdByte(z80))
        );

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(14)
                .executionTime(3.50f)
                .size(4)
                .build();
    }
}
