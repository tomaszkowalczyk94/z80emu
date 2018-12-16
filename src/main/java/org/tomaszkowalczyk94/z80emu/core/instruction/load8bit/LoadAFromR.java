package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import lombok.RequiredArgsConstructor;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

/**
 * <h2>LD A, R</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A ← R</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>A, R</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of Memory Refresh Register R are loaded to the Accumulator.
 *
 * <h3>Condition Bits Affected</h3>
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>S</td>
 *         <td>is set if the R Register is negative; otherwise, it is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>Z</td>
 *         <td>is set if the R Register is 0; otherwise, it is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>H</td>
 *         <td>is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>P/V</td>
 *         <td>contains contents of IFF2.</td>
 *     </tr>
 *     <tr>
 *         <td>N</td>
 *         <td>is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>C</td>
 *         <td>is not affected.</td>
 *     </tr>
 * </table>
 * If an interrupt occurs during execution of this instruction, the Parity flag contains a 0
 *
 */
@RequiredArgsConstructor
public class LoadAFromR implements Instruction {

    final InstructionHelper instructionHelper;

    @Override
    public void execute(XBit8 opcode, Z80 z80) {
        z80.getRegs().setA(
                z80.getRegs().getI()
        );

        instructionHelper.setSignFlagByA(z80);
        instructionHelper.setZeroFlagByA(z80);
        instructionHelper.setPvFlagByIff2(z80);

        z80.getRegs().setFlag(FlagRegManager.Flag.H, false);
        z80.getRegs().setFlag(FlagRegManager.Flag.N, false);
    }

    @Override
    public int getMachineCycles() {
        return 2;
    }

    @Override
    public int getClocks() {
        return 9;
    }

    @Override
    public float getExecutionTime() {
        return 2.25f;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
