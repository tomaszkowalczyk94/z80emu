package org.tomaszkowalczyk94.z80emu.core.instruction.blocktransfer;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.LoadDataAndIncrementHelper;

/**
 * <h2>LDDR</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(DE) ← (HL), DE ← DE – 1, HL ← HL – 1, BC ← BC – 1</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LDDR </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>None</td>
 *     </tr>
 * </table>
 * <br>
 * This 2-byte instruction transfers a byte of data from the memory location addressed by the
 * contents of the HL register pair to the memory location addressed by the contents of the
 * DE register pair. Then both of these registers, and the BC (Byte Counter), are decre
 * mented. If decrementing causes BC to go to 0, the instruction is terminated. If BC is not 0,
 * the program counter is decremented by two and the instruction is repeated. Interrupts are
 * recognized and two refresh cycles execute after each data transfer.
 * When the BC is set to 0, prior to instruction execution, the instruction loops through
 * 64 KB.
 * Condition Bits Affected<br />
 * S is not affected.<br />
 * Z is not affected.<br />
 * H is reset.<br />
 * P/V is set if BC – 1 ≠ 0; otherwise, it is reset.<br />
 * N is reset.<br />
 * C is not affected.<br />
 *
 */
public class LoadDataAndDecrementAndRepeat extends Instruction {
    private LoadDataAndIncrementHelper loadDataAndIncrementHelper;

    public LoadDataAndDecrementAndRepeat(InstructionHelper helper, LoadDataAndIncrementHelper loadDataAndIncrementHelper) {
        super(helper);
        this.loadDataAndIncrementHelper = loadDataAndIncrementHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        loadDataAndIncrementHelper.execute(z80, -1);

        if(z80.getRegs().getBC().getUnsignedValue() != 0) {
            return InstructionResult.builder()
                    .machineCycles(5)
                    .clocks(21)
                    .executionTime(5.25f)
                    .autoIncrementPc(false)
                    .size(2)
                    .build();
        } else {
            return InstructionResult.builder()
                    .machineCycles(4)
                    .clocks(16)
                    .executionTime(4.00f)
                    .autoIncrementPc(true)
                    .size(2)
                    .build();
        }
    }
}