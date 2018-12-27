package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>EX AF, AF'</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>AF ↔ AF'</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>EX </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>AF, AF'</td>
 *     </tr>
 * </table>
 * <br>
 * The 2-byte contents of the register pairs AF and AF' are exchanged. Register pair AF consists of registers A′ and F′.
 */
public class ExchangeAfAf extends Instruction {

    public ExchangeAfAf(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) {

        XBit16 afValue = z80.getRegs().getAf();
        XBit16 afAlternativeValue = z80.getRegs().getAlternativeRegisterSet().getAf();

        z80.getRegs().setAf(afAlternativeValue);
        z80.getRegs().getAlternativeRegisterSet().setAf(afValue);

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(4)
                .executionTime(1.00f)
                .size(1)
                .build();
    }
}