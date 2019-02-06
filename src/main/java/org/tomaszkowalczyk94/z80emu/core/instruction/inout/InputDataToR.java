package org.tomaszkowalczyk94.z80emu.core.instruction.inout;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;

/**
 * <h2>IN r (C)</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>r ← (C)</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>IN</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>r, (C)</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of Register C are placed on the bottom half (A0 through A7) of the address
 * bus to select the I/O device at one of 256 possible ports. The contents of Register B are
 * placed on the top half (A8 through A15) of the address bus at this time. Then one byte
 * from the selected port is placed on the data bus and written to register r in the CPU. Regis-
 * ter r identifies any of the CPU registers shown in the following table, which also indicates
 * the corresponding 3-bit r field for each. The flags are affected, checking the input data.
 * Register          r<br />
 * Flag 110 Undefined op code; set the flag<br />
 * B 000<br />
 * C 001<br />
 * D 010<br />
 * E 011<br />
 * H 100<br />
 * L 101<br />
 * A 111<br />
 */
public class InputDataToR extends Instruction {
    public InputDataToR(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        return null;
    }
}
