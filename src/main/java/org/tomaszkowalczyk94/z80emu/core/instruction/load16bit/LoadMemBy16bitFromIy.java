package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (nn), IY</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn + 1) ← IYh, (nn) ← IYI</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), IY</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte in Index Register IY is loaded to memory address (nn); the upper order
 * byte is loaded to memory location (nn+ 1). The first n operand after the op code is the loworder byte of nn.
 */
public class LoadMemBy16bitFromIy extends Instruction {

    public LoadMemBy16bitFromIy(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                helper.getFourthByte(z80),
                helper.getThirdByte(z80)
        );

        helper.write16bitToMemory(
                z80,
                address,
                z80.getRegs().getIy()
        );

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(20)
                .executionTime(5.00f)
                .size(4)
                .build();

    }
}
