package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD IX, (nn)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IXh ← (nn + 1), IXI ← (nn)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>IX, (nn)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the address (nn) are loaded to the low-order portion of Index Register IX,
 * and the contents of the next highest memory address (nn+ 1) are loaded to the high-order
 * portion of IX. The first n operand after the op code is the low-order byte of nn.
 *
 */
public class LoadIxFromMemBy16bit extends Instruction {

    public LoadIxFromMemBy16bit(InstructionHelper helper) {
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

        z80.getRegs().setIx(
                newValue
        );

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(20)
                .executionTime(5.0f)
                .size(4)
                .build();
    }
}