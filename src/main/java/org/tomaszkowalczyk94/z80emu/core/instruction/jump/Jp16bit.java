package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * <h2>JP nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>PC ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JP </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>nn</td>
 *     </tr>
 * </table>
 * <br>
 * Operand nn is loaded to register pair Program Counter (PC). The next instruction is
 * fetched from the location designated by the new contents of the PC.
 */
public class Jp16bit extends Instruction {
    public Jp16bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setPc(
                XBit16.valueOfHighAndLow(
                        helper.getThirdByte(z80),
                        helper.getSecondByte(z80)
                )
        );

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.50f)
                .size(3)
                .autoIncrementPc(false)
                .build();
    }
}
