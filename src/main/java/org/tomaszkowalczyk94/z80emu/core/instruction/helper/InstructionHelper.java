package org.tomaszkowalczyk94.z80emu.core.instruction.helper;

import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.xbit.XBitUtils;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.memory.exception.MemoryException;

import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.PV;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.S;
import static org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag.Z;

public class InstructionHelper {

    /**
     * instruction can have more than 1 byte instruction. This method return second byte of instruction (first byte operand)
     */
    public XBit8 getSecondByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 1)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return third byte of instruction (second byte operand)
     */
    public XBit8 getThirdByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 2)
        );
    }

    /**
     * instruction can have more than 1 byte instruction. This method return fourth byte of instruction (third byte operand)
     */
    public XBit8 getFourthByte(Z80 z80) throws MemoryException {
        return z80.getMem().read(
                XBitUtils.incrementBy(z80.getRegs().getPc(), 3)
        );
    }

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

    /**
     * Write 16bit value to memory in little endian mode
     */
    public void write16bitToMemory(Z80 z80, XBit16 address, XBit16 value) throws MemoryException {

        XBit16 addressOfHigh = XBitUtils.incrementBy(address, 1);

        z80.getMem().write(
                address,
                value.getLowByte()
        );

        z80.getMem().write(
                addressOfHigh,
                value.getHighByte()
        );

    }

    /**
     * read 16bit value to from memory in little endian mode
     */
    public XBit16 read16bitFromMemory(Z80 z80, XBit16 address) throws MemoryException {
        return XBit16.valueOfHighAndLow(
                z80.getMem().read(
                        XBitUtils.incrementBy(address,1)
                ) ,
                z80.getMem().read(address)
        );
    }

    public void pushToStack(Z80 z80, XBit16 value) throws MemoryException {

        //write value to stack
        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -1),
                value.getHighByte()
        );

        z80.getMem().write(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2),
                value.getLowByte()
        );

        //set stack pointer
        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), -2)
        );
    }

    public XBit16 popFromStack(Z80 z80) throws MemoryException {

        XBit16 value = XBit16.valueOfHighAndLow(
                z80.getMem().read(
                        XBitUtils.incrementBy(z80.getRegs().getSp(), 1)
                ),
                z80.getMem().read(
                        z80.getRegs().getSp()
                )
        );

        //set stack pointer
        z80.getRegs().setSp(
                XBitUtils.incrementBy(z80.getRegs().getSp(), 2)
        );

        return value;
    }
}
