package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD r, r'</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>r, ← r′ </td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>r, r</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of any register r' are loaded to any other register r. r, r' identifies any of the
 * registers A, B, C, D, E, H, or L, assembled as follows in the object code:
 * Register r, C
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadRegFromReg extends Instruction {


    public LoadRegFromReg(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        byte firstRegisterId = (byte)opcode.getValueOfBits(5, 3);
        byte secondRegisterId = (byte)opcode.getValueOfBits(2, 0);

        XBit8 secondRegisterValue = z80.getRegs().get8BitRegisterById(secondRegisterId);
        z80.getRegs().set8BitRegisterById(firstRegisterId, secondRegisterValue);

        return InstructionResult.builder()
                .machineCycles(1)
                .clocks(4)
                .executionTime(1.0f)
                .size(1)
                .build();
    }

}
