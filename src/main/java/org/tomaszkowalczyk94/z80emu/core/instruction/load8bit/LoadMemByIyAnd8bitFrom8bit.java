package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (IY+d), n</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(lY+d) ← n</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(lY+d), n</td>
 *     </tr>
 * </table>
 * <br>
 * The n integer is loaded to the memory location specified by the contents of Index Register
 * summed with the two’s-complement displacement integer, d.
 */
public class LoadMemByIyAnd8bitFrom8bit extends Instruction {


    public LoadMemByIyAnd8bitFrom8bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 offset = helper.getThirdByte(z80);
        XBit8 immediateValue = helper.getFourthByte(z80);

        XBit16 memAddress = XBitUtils.incrementBy(
                z80.getRegs().getIy(),
                offset.getSignedValue()
        );

        z80.getMem().write(memAddress, immediateValue);

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(19)
                .executionTime(2.5f)
                .size(4)
                .build();
    }

}
