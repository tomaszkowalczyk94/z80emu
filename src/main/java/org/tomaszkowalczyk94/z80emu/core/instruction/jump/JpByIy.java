package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * <h2>JP (IY)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>PC ← IY</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JP</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(IY)</td>
 *     </tr>
 * </table>
 * <br>
 * The Program Counter (PC) is loaded with the contents of the IY register pair. The next
 * instruction is fetched from the location designated by the new contents of the PC.
 */
public class JpByIy extends Instruction {
    public JpByIy(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setPc(
                z80.getRegs().getIy()
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(8)
                .executionTime(2.00f)
                .size(2)
                .autoIncrementPc(false)
                .build();
    }
}