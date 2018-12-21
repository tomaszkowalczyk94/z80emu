package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>RET</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>pCL ← (sp), pCH ← (sp+1)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>RET</td>
 *     </tr>
 * </table>
 * <br>
 * The byte at the memory location specified by the contents of the Stack Pointer (SP) Regis
 * ter pair is moved to the low-order eight bits of the Program Counter (PC). The SP is now
 * incremented and the byte at the memory location specified by the new contents of this
 * instruction is fetched from the memory location specified by the PC. This instruction is
 * normally used to return to the main line program at the completion of a routine entered by
 * a CALL instruction.
 *
 */
public class Ret extends Instruction {
    public Ret(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setPc(
                helper.read16bitFromMemory(z80, z80.getRegs().getSp())
        );

        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), 2)
        );

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.50f)
                .size(1)
                .autoIncrementPc(false)
                .build();
    }
}
