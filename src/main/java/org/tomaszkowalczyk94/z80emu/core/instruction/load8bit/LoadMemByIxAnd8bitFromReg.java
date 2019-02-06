package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (IX+d), r</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(IX+d) ← r</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(IX+d), r</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of register r are loaded to the memory address specified by the contents of
 * Index Register IX summed with d, a two’s-complement displacement integer. The r sym
 * bol identifies registers A, B, C, D, E, H, or L, assembled as follows in the object code:
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadMemByIxAnd8bitFromReg extends Instruction {


    public LoadMemByIxAnd8bitFromReg(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit8 secondByte = helper.getSecondByte(z80);
        XBit8 thirdByte = helper.getThirdByte(z80);

        XBit16 memoryAddress = XBitUtils.incrementBy(
                z80.getRegs().getIx(),
                thirdByte.getSignedValue()
        );

        byte regId = (byte)secondByte.getValueOfBits(2, 0);

        z80.getMem().write(
                memoryAddress,
                z80.getRegs().get8BitRegisterById(regId)
        );

        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(19)
                .executionTime(4.75f)
                .size(3)
                .build();
    }
}
