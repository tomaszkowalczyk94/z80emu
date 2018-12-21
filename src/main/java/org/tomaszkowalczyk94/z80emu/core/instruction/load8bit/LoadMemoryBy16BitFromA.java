package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (nn), A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(nn) ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(nn), A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the memory address specified by the oper
 * and nn. The first n operand after the op code is the low-order byte of nn.
 */
public class LoadMemoryBy16BitFromA extends Instruction{

    public LoadMemoryBy16BitFromA(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 address = XBit16.valueOfHighAndLow(
                helper.getThirdByte(z80),
                helper.getSecondByte(z80)
        );

        z80.getMem().write(
                address,
                z80.getRegs().getA()
        );

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(13)
                .executionTime(3.25f)
                .size(3)
                .build();
    }
}
