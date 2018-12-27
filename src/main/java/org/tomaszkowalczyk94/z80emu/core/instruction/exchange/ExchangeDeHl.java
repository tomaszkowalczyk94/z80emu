package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

/**
 * <h2>EX DE, HL</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>DE ↔ HL</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>EX </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>DE, HL</td>
 *     </tr>
 * </table>
 * <br>
 * The 2-byte contents of register pairs DE and HL are exchanged.
 */
public class ExchangeDeHl  extends Instruction {

    public ExchangeDeHl(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) {

        XBit16 deValue = z80.getRegs().getDE();
        XBit16 hlValue = z80.getRegs().getHL();

        z80.getRegs().setHL(deValue);
        z80.getRegs().setDE(hlValue);

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(4)
                .executionTime(1.00f)
                .size(1)
                .build();
    }
}