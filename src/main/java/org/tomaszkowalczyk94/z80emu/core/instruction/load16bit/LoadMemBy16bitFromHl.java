package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (nn), HL</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn + 1) ← H, (nn) ← L</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), HL</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the low-order portion of register pair HL (Register L) are loaded to mem
 * ory address (nn), and the contents of the high-order portion of HL (Register H) are loaded
 * to the next highest memory address (nn+ 1). The first n operand after the op code is the
 * low-order byte of nn.
 *
 */
public class LoadMemBy16bitFromHl extends Instruction {

    public LoadMemBy16bitFromHl(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                helper.getThirdByte(z80),
                helper.getSecondByte(z80)
        );

        helper.write16bitToMemory(
                z80,
                address,
                z80.getRegs().getHL()
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(16)
                .executionTime(4.00f)
                .size(3)
                .build();
    }
}
