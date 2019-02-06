package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.ExchangeRegistersHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

/**
 * <h2>EX (SP), IX</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IXH ↔ (SP+1), IXL ↔ (SP)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>EX </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(SP), IX</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte in Index Register IX is exchanged with the contents of the memory
 * address specified by the contents of register pair SP (Stack Pointer), and the high-order
 * byte of IX is exchanged with the next highest memory address (SP+1).
 */
public class ExchangeIxWithStackTop extends Instruction {

    private ExchangeRegistersHelper exchangeRegistersHelper;

    public ExchangeIxWithStackTop(InstructionHelper helper, ExchangeRegistersHelper exchangeRegistersHelper) {
        super(helper);
        this.exchangeRegistersHelper = exchangeRegistersHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        exchangeRegistersHelper.exchange16bitRegWithStackTop(z80, RegisterBank.Reg16bit.IX);

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(23)
                .executionTime(5.75f)
                .size(1)
                .build();
    }
}