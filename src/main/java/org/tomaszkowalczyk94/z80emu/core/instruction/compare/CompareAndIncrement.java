package org.tomaszkowalczyk94.z80emu.core.instruction.compare;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.FlagHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager;

import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.N;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.BC;
import static org.tomaszkowalczyk94.z80emu.core.register.RegisterBank.Reg16bit.HL;


/**
 * <h2>CPI</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A – (HL), HL ← HL +1, BC ← BC – 1</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>CPI </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>None</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the memory location addressed by the HL register is compared with the
 * contents of the Accumulator. With a true compare, a condition bit is set. Then HL is incre
 * mented and the Byte Counter (register pair BC) is decremented.
 * Condition Bits Affected <br />
 * S is set if result is negative; otherwise, it is reset.<br />
 * Z is set if A is (HL); otherwise, it is reset.<br />
 * H is set if borrow from bit 4; otherwise, it is reset.<br />
 * P/V is set if BC – 1 is not 0; otherwise, it is reset.<br />
 * N is set.<br />
 * C is not affected.<br />
 */
public class CompareAndIncrement extends Instruction {

    private FlagHelper flagHelper;

    public CompareAndIncrement(InstructionHelper helper, FlagHelper flagHelper) {
        super(helper);
        this.flagHelper = flagHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit8 aVal = z80.getRegs().getA();
        XBit8 memVal = z80.getMem().read(z80.getRegs().getHL());

        XBitUtils.Arithmetic8bitResult result = XBitUtils.subTwo8bits(
                aVal,
                memVal
        );

        z80.getRegs().incrementReg16bit(HL);
        z80.getRegs().incrementReg16bit(BC, -1);

        flagHelper.setSignFlagBy8bit(z80, result.result);
        flagHelper.setZeroFlabBy8bit(z80, result.result);
        flagHelper.setHalfCarryFlagSub(z80, aVal, memVal);
        z80.getRegs().setFlag(
                PV,
                (z80.getRegs().getBC().getSignedValue() != 0)
        );
        z80.getRegs().setFlag(N, true);


        return InstructionResult.builder()
                .machineCycles(4)
                .clocks(16)
                .executionTime(4.00f)
                .size(2)
                .build();
    }
}
