package org.tomaszkowalczyk94.z80emu.core.instruction.inout;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * <h2>IN A, (n)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A ← (n)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>IN </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>A, (n)</td>
 *     </tr>
 * </table>
 * <br>
 * The operand n is placed on the bottom half (A0 through A7) of the address bus to select
 * the I/O device at one of 256 possible ports. The contents of the Accumulator also appear
 * on the top half (A8 through A15) of the address bus at this time. Then one byte from the
 * selected port is placed on the data bus and written to the Accumulator (Register A) in the
 * CPU.
 */
public class InputDataToA extends Instruction {
    public InputDataToA(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit8 valueFromIo = z80.getIo().read(XBit16.valueOfHighAndLow(
                z80.getRegs().getA(),
                helper.getSecondByte(z80)
        ));

        z80.getRegs().setA(valueFromIo);

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(11)
                .executionTime(2.75f)
                .size(2)
                .build();
    }
}
