package org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.LoadDataAndIncrementHelper;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag;

import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.BC;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.DE;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.HL;

/**
 * <h2>LDI</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(DE) ← (HL), DE ← DE + 1, HL ← HL + 1, BC ← BC – 1</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LDI </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>None</td>
 *     </tr>
 * </table>
 * <br>
 * A byte of data is transferred from the memory location addressed, by the contents of the
 * HL register pair to the memory location addressed by the contents of the DE register pair.
 * Then both these register pairs are incremented and the Byte Counter (BC) Register pair is
 * decremented. <br />
 * Condition Bits Affected<br />
 * S is not affected.<br />
 * Z is not affected.<br />
 * H is reset.<br />
 * P/V is set if BC – 1 ≠ 0; otherwise, it is reset.<br />
 * N is reset.<br />
 * C is not affected.<br />
 *
 */
public class LoadDataAndIncrement extends Instruction {

    private LoadDataAndIncrementHelper loadDataAndIncrementHelper;

    public LoadDataAndIncrement(InstructionHelper helper, LoadDataAndIncrementHelper loadDataAndIncrementHelper) {
        super(helper);
        this.loadDataAndIncrementHelper = loadDataAndIncrementHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        loadDataAndIncrementHelper.execute(z80, 1);

        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(16)
                .executionTime(4.00f)
                .size(2)
                .build();
    }
}