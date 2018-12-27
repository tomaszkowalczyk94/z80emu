package org.tomaszkowalczyk94.z80emu.core.instruction.exchange;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

/**
 * <h2>EX (SP), HL</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>H ↔ (SP+1), L ↔ (SP)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>EX </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(SP), HL</td>
 *     </tr>
 * </table>
 * <br>
 * The low-order byte contained in register pair HL is exchanged with the contents of the
 * memory address specified by the contents of register pair SP (Stack Pointer), and the high
 * order byte of HL is exchanged with the next highest memory address (SP+1).
 */
public class ExchangeHlWithStackTop extends Instruction {

    public ExchangeHlWithStackTop(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws MemoryException {

        XBit16 hlValue = z80.getRegs().getHL();
        XBit16 topStackValue = helper.read16bitFromMemory(z80, z80.getRegs().getSp());


        z80.getRegs().setHL(topStackValue);
        helper.write16bitToMemory(
                z80,
                z80.getRegs().getSp(),
                hlValue
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(19)
                .executionTime(4.75f)
                .size(1)
                .build();
    }
}