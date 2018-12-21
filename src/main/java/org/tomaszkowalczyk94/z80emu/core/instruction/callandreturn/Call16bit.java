package org.tomaszkowalczyk94.z80emu.core.instruction.callandreturn;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.instruction.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;


/**
 * <h2>CALL nn</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>(SP – 1) ← PCH, (SP – 2) ← PCL, PC ← nn</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>CALL</td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>nn</td>
 *     </tr>
 * </table>
 * <br>
 * The current contents of the Program Counter (PC) are pushed onto the top of the external
 * memory stack. The operands nn are then loaded to the PC to point to the address in mem
 * ory at which the first op code of a subroutine is to be fetched. At the end of the subroutine,
 * a RETurn instruction can be used to return to the original program flow by popping the top
 * of the stack back to the PC. The push is accomplished by first decrementing the current
 * contents of the Stack Pointer (register pair SP), loading the high-order byte of the PC con
 * tents to the memory address now pointed to by the SP; then decrementing SP again, and
 * loading the low-order byte of the PC contents to the top of stack.
 * Because this process is a 3-byte instruction, the Program Counter was incremented by
 * three before the push is executed.
 *
 */
public class Call16bit extends Instruction {


    public Call16bit(InstructionHelper helper) {
        super(helper);
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) throws Z80Exception {

        XBit16 incrementedPc = XBitUtils.incrementBy(z80.getRegs().getPc(), 3);

        //write value to stack
        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -1),
                incrementedPc.getHighByte()
        );

        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2),
                incrementedPc.getLowByte()
        );

        //set stack pointer
        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2)
        );

        //set pc to new call address
        z80.getRegs().setPc(
                XBit16.valueOfHighAndLow(
                        helper.getThirdByte(z80),
                        helper.getSecondByte(z80)
                )
        );


        return InstructionResult.builder()
                .machineCycles(5)
                .clocks(17)
                .executionTime(4.25f)
                .size(3)
                .autoIncrementPc(false)
                .build();
    }
}
