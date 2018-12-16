package org.tomaszkowalczyk94.z80emu.core.instruction;

import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.S;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.Z;

public class InstructionHelper {

    /**
     * The Sign Flag (S) stores the state of the most-significant bit of the Accumulator (bit 7).
     * S is set if the A Register is negative; otherwise, it is reset
     */
    public void setSignFlagByA(Z80 z80) {
        z80.getRegs().setFlag(S, z80.getRegs().getA().getBit(7));
    }

    /**
     * For 8-bit arithmetic and logical operations, the Z flag is set to a 1
     * the Accumulator is 0. If the byte is not 0, the Z flag is reset to 0.
     */
    public void setZeroFlagByA(Z80 z80) {
        z80.getRegs().setFlag(Z,
                z80.getRegs().getA().getSignedValue() == 0
        );
    }

    /**
     * During the LD A, I and LD A, R instructions, the P/V Flag is set with the value of the
     * interrupt enable flip-flop (IFF2) for storage or testing.
     */
    public void setPvFlagByIff2(Z80 z80) {
        z80.getRegs().setFlag(PV, z80.isIff2());
    }
}
