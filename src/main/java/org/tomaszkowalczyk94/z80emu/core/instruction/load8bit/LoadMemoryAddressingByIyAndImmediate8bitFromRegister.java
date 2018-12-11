package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>LD (IY+d), r</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(lY+d) ← r</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(lY+d), r</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of resister r are loaded to the memory address specified by the sum of the
 * contents of Index Register IY and d, a two’s-complement displacement integer. The r sym
 * bol is specified according to the following table.
 * A 111<br>
 * B 000<br>
 * C 001<br>
 * D 010<br>
 * E 011<br>
 * H 100<br>
 * L 101<br>
 */
public class LoadMemoryAddressingByIyAndImmediate8bitFromRegister implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit8 secondByte = getSecondByte(z80);
        XBit8 thirdByte = getThirdByte(z80);

        XBit16 memoryAddress = XBitUtils.incrementBy(
                z80.getRegs().getIy(),
                thirdByte.getSignedValue()
        );

        byte regId = (byte)secondByte.getValueOfBits(2, 0);

        z80.getMem().write(
                memoryAddress,
                z80.getRegs().get8BitRegisterById(regId)
        );
    }

    @Override
    public int getMachineCycles() {
        return 5;
    }

    @Override
    public int getClocks() {
        return 19;
    }

    @Override
    public float getExecutionTime() {
        return 4.75f;
    }

    @Override
    public int getSize() {
        return 3;
    }
}