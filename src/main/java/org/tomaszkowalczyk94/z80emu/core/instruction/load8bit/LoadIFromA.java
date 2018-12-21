package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD I,A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>I ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>I, A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the Interrupt Control Vector Register, I.
 */
public class LoadIFromA extends Instruction {


    public LoadIFromA(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getRegs().setI(
                z80.getRegs().getA()
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(9)
                .executionTime(2.25f)
                .size(2)
                .build();
    }
}
