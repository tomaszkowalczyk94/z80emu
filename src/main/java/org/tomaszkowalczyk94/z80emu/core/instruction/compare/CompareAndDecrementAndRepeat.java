package org.tomaszkowalczyk94.z80emu.core.instruction.compare;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.CompareAndIncrementHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * <h2>CPDR</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A – (HL), HL ← HL – 1, BC ← BC – 1</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>CPDR </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>None</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the memory location addressed by the HL register pair is compared with
 * the contents of the Accumulator. During a compare operation, a condition bit is set. The
 * HL and Byte Counter (BC) Register pairs are decremented. If decrementing allows the BC
 * to go to 0 or if A = (HL), the instruction is terminated. If BC is not 0 and A = (HL), the
 * program counter is decremented by two and the instruction is repeated. Interrupts are rec
 * ognized and two refresh cycles execute after each data transfer. When the BC is set to 0,
 * prior to instruction execution, the instruction loops through 64 KB if no match is found.
 * Condition Bits Affected <br />
 * S is set if result is negative; otherwise, it is reset.<br />
 * Z is set if A is (HL); otherwise, it is reset.<br />
 * H is set if borrow from bit 4; otherwise, it is reset.<br />
 * P/V is set if BC – 1 is not 0; otherwise, it is reset.<br />
 * N is set.<br />
 * C is not affected.<br />
 */
public class CompareAndDecrementAndRepeat extends Instruction {

    private CompareAndIncrementHelper compareAndIncrementHelper;

    public CompareAndDecrementAndRepeat(InstructionHelper helper, CompareAndIncrementHelper compareAndIncrementHelper) {
        super(helper);
        this.compareAndIncrementHelper = compareAndIncrementHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        if(compareAndIncrementHelper.execute(z80, -1)) {
            return InstructionResult.builder()
                    .machineCycles(4)
                    .clocks(16)
                    .executionTime(4.00f)
                    .size(2)
                    .build();
        }else {
            return InstructionResult.builder()
                    .machineCycles(5)
                    .clocks(21)
                    .executionTime(5.25f)
                    .autoIncrementPc(false)
                    .size(2)
                    .build();
        }
    }
}
