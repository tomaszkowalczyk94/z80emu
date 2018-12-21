package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (BC), A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(BC) ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(BC), A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the memory location specified by the con
 * tents of the register pair BC.
 */
public class LoadMemoryByBcFromA extends Instruction {

    public LoadMemoryByBcFromA(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getMem().write(
                z80.getRegs().getBC(),
                z80.getRegs().getA()
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(7)
                .executionTime(1.75f)
                .size(1)
                .build();
    }
}
