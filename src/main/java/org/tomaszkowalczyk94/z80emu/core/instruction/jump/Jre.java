package org.tomaszkowalczyk94.z80emu.core.instruction.jump;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;

/**
 * <h2>JR e</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>PC ← PC + e</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>JR</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>e</td>
 *     </tr>
 * </table>
 * <br>
 * This instruction provides for unconditional branching to other segments of a program. The
 * value of displacement e is added to the Program Counter (PC) and the next instruction is
 * fetched from the location designated by the new contents of the PC. This jump is mea
 * sured from the address of the instruction op code and contains a range of –126 to +129
 * bytes. The assembler automatically adjusts for the twice incremented PC.
 */
public class Jre extends Instruction {
    public Jre(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        z80.getRegs().setPc(
                XBitUtils.incrementBy(z80.getRegs().getPc(), helper.getSecondByte(z80).getSignedValue()+2)
        );

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.50f)
                .size(3)
                .autoIncrementPc(false)
                .build();
    }
}
