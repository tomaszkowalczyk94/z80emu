package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.ExchangeRegistersHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.register.RegisterBank;

/**
 * <h2>EX (SP), IY</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>IYH ↔ (SP+1), IYL ↔ (SP)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>EX </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(SP), IY</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte in Index Register IY is exchanged with the contents of the memory
 * address specified by the contents of register pair SP (Stack Pointer), and the high-order
 * byte of IY is exchanged with the next highest memory address (SP+1).
 */
public class ExchangeIyWithStackTop extends Instruction {

    private ExchangeRegistersHelper exchangeRegistersHelper;

    public ExchangeIyWithStackTop(InstructionHelper helper, ExchangeRegistersHelper exchangeRegistersHelper) {
        super(helper);
        this.exchangeRegistersHelper = exchangeRegistersHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        exchangeRegistersHelper.exchange16bitRegWithStackTop(z80, RegisterBank.Reg16bit.IY);

        return InstructionResult.builder()
                .machineCycles(6)
                .clocks(23)
                .executionTime(5.75f)
                .size(1)
                .build();
    }
}