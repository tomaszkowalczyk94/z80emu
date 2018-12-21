package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (HL), r</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(HL) ← r</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(HL), r</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of register r are loaded to the memory location specified by the contents of
 * the HL register pair. The r symbol identifies registers A, B, C, D, E, H, or L, assembled as
 * follows in the object code:
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadMemByHlFromReg extends Instruction {


    public LoadMemByHlFromReg(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        int regId = opcode.getValueOfBits(3, 0);

        z80.getMem().write(
                z80.getRegs().getHL(),
                z80.getRegs().get8BitRegisterById((byte)regId)
        );

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(7)
                .executionTime(1.75f)
                .size(1)
                .build();
    }
}
