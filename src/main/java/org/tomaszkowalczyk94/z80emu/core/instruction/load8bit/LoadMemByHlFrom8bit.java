package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;

/**
 * <h2>LD (HL), n</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(HL) ← n</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>(HL), n</td>
 *     </tr>
 * </table>
 * <br>
 * The n integer is loaded to the memory address specified by the contents of the HL register
 * pair.
 */
public class LoadMemByHlFrom8bit extends Instruction{


    public LoadMemByHlFrom8bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        XBit8 immediateValue = helper.getSecondByte(z80);

        z80.getMem().write(
                z80.getRegs().getHL(),
                immediateValue
        );

        return InstructionResult.builder()
                .machineCycles(3)
                .clocks(10)
                .executionTime(2.5f)
                .size(2)
                .build();
    }
}
